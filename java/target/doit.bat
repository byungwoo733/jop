call build %1 %2 %3

java -cp ../tools/dist/lib/jop-tools.jar com.jopdesign.tools.jop2dat dist\bin\%project%.jop 
copy *.dat ..\..\modelsim
del *.dat

pause Compilation ok? Start download?

rem uncomment your target for the Cyclone board:

rem jbi32 -dDO_PROGRAM=1 -aPROGRAM ..\..\jbc\cycmin.jbc
rem jbi32 -dDO_PROGRAM=1 -aPROGRAM ..\..\jbc\cyc_conf.jbc
rem jbi32 -dDO_PROGRAM=1 -aCONFIGURE ..\..\jbc\cycmin100.jbc
rem jbi32 -dDO_PROGRAM=1 -aCONFIGURE ..\..\jbc\cyc12min.jbc
rem jbi32 -dDO_PROGRAM=1 -aCONFIGURE ..\..\jbc\cycbaseio60.jbc
rem jbi32 -dDO_PROGRAM=1 -aCONFIGURE ..\..\jbc\cycmin.jbc
..\..\down -e dist\bin\%project%.jop COM1

rem java -cp ../tools/dist/lib/jop-tools.jar -Dlog="false" -Dhandle="true" com.jopdesign.tools.JopSim dist\bin\%project%.jop
