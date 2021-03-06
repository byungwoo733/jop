/*
  This file is part of JOP, the Java Optimized Processor
    see <http://www.jopdesign.com/>

  Copyright (C) 2008, Martin Schoeberl (martin@jopdesign.com)

  This program is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/


/**
 * 
 */
package rttm.jsim;

import rtlib.SRSWQueue;

import com.jopdesign.io.IOFactory;
import com.jopdesign.io.SysDevice;
import com.jopdesign.sys.Native;
import com.jopdesign.sys.Startup;

/**
 * Test program for RTTM with two bound Queues.
 * 
 * @author Martin Schoeberl
 *
 */
public class TwoIndQueues {
	
	static SysDevice sys = IOFactory.getFactory().getSysDevice();
	
	private static SRSWQueue<Object> qAB = new SRSWQueue<Object>(10);
	private static SRSWQueue<Object> qCD = new SRSWQueue<Object>(10);
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		if (sys.nrCpu<4) {
			System.out.println("Not enogh CPUs for this example");
			System.exit(-1);
		}
		Inserter<Object> i1 = new Inserter<Object>(qAB);
		Remover<Object> r1 = new Remover<Object>(qAB);
		Inserter<Object> i2 = new Inserter<Object>(qCD);
		Remover<Object> r2 = new Remover<Object>(qCD);

		Startup.setRunnable(r1, 0);
		Startup.setRunnable(i2, 1);
		Startup.setRunnable(r2, 2);

		// start the other CPUs
		sys.signal = 1;
		// run one Runnable on this CPU
		i1.run();

		System.out.println("wait");
		// wait for other CPUs to finish
		while (!(r1.finished && i2.finished && r2.finished)) {
			;
		}
	}
}
