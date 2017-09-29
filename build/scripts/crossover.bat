@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  crossover startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and CROSSOVER_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\websocket-api-9.2.20.v20161216.jar;%APP_HOME%\lib\commons-io-2.5.jar;%APP_HOME%\lib\selenium-chrome-driver-3.3.1.jar;%APP_HOME%\lib\jsr305-1.3.9.jar;%APP_HOME%\lib\htmlunit-2.24.jar;%APP_HOME%\lib\selenium-java-3.3.1.jar;%APP_HOME%\lib\selenium-api-3.6.0.jar;%APP_HOME%\lib\byte-buddy-1.7.5.jar;%APP_HOME%\lib\xercesImpl-2.11.0.jar;%APP_HOME%\lib\neko-htmlunit-2.24.jar;%APP_HOME%\lib\websocket-common-9.2.20.v20161216.jar;%APP_HOME%\lib\commons-exec-1.3.jar;%APP_HOME%\lib\guava-23.0.jar;%APP_HOME%\lib\error_prone_annotations-2.0.18.jar;%APP_HOME%\lib\selenium-safari-driver-3.3.1.jar;%APP_HOME%\lib\testng-6.11.jar;%APP_HOME%\lib\snakeyaml-1.17.jar;%APP_HOME%\lib\commons-lang3-3.5.jar;%APP_HOME%\lib\selenium-support-3.6.0.jar;%APP_HOME%\lib\commons-logging-1.2.jar;%APP_HOME%\lib\selenium-opera-driver-3.3.1.jar;%APP_HOME%\lib\phantomjsdriver-1.4.0.jar;%APP_HOME%\lib\xml-apis-1.4.01.jar;%APP_HOME%\lib\websocket-client-9.2.20.v20161216.jar;%APP_HOME%\lib\htmlunit-core-js-2.23.jar;%APP_HOME%\lib\cssparser-0.9.21.jar;%APP_HOME%\lib\httpmime-4.5.2.jar;%APP_HOME%\lib\selenium-edge-driver-3.3.1.jar;%APP_HOME%\lib\xalan-2.7.2.jar;%APP_HOME%\lib\httpcore-4.4.6.jar;%APP_HOME%\lib\j2objc-annotations-1.1.jar;%APP_HOME%\lib\jna-4.1.0.jar;%APP_HOME%\lib\crossover.jar;%APP_HOME%\lib\jcommander-1.64.jar;%APP_HOME%\lib\httpclient-4.5.3.jar;%APP_HOME%\lib\serializer-2.7.2.jar;%APP_HOME%\lib\jetty-io-9.2.20.v20161216.jar;%APP_HOME%\lib\selenium-ie-driver-3.3.1.jar;%APP_HOME%\lib\commons-codec-1.10.jar;%APP_HOME%\lib\animal-sniffer-annotations-1.14.jar;%APP_HOME%\lib\selenium-firefox-driver-3.3.1.jar;%APP_HOME%\lib\selenium-remote-driver-3.6.0.jar;%APP_HOME%\lib\jetty-util-9.2.20.v20161216.jar;%APP_HOME%\lib\htmlunit-driver-2.24.jar;%APP_HOME%\lib\gson-2.8.0.jar;%APP_HOME%\lib\sac-1.3.jar;%APP_HOME%\lib\jna-platform-4.1.0.jar

@rem Execute crossover
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %CROSSOVER_OPTS%  -classpath "%CLASSPATH%" App %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable CROSSOVER_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%CROSSOVER_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
