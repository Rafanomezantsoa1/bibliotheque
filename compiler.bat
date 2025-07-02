@echo off
set PROJECT_DIR=D:\S4\MrNaina\1-Cinema
set JAVA_DIR=%PROJECT_DIR%\src\main\java
set LIB_DIR=%PROJECT_DIR%\lib

REM Nettoyer et recréer le dossier de sortie
rmdir /s /q out
mkdir out

REM Compilation des fichiers Java
echo Compilation des fichiers Java...
setlocal enabledelayedexpansion
set FILES=
for /R "%JAVA_DIR%" %%f in (*.java) do (
    set FILES=!FILES! "%%f"
)
javac -d out -cp "%LIB_DIR%\*;.;src/main/resources"  !FILES!

IF %ERRORLEVEL% NEQ 0 (
    echo Erreur de compilation.
    pause
    exit /b %ERRORLEVEL%
)

REM Copier le fichier beans.xml et autres resources dans out/
echo Copie des fichiers deJ resources...
xcopy /s /y src\main\resources\* out\

REM Exécution du programme
echo Lancement du programme...
java -cp out;lib/* console.Main

@REM pause