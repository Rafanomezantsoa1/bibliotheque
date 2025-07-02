@echo off
setlocal enabledelayedexpansion

:: Dossiers
set PROJECT_DIR=D:\S4\MrNaina\bibliotheque
set TOMCAT_DIR=C:\apache-tomcat-10.1.34
set JAVA_DIR=%PROJECT_DIR%\src\main\java
set WEBAPP_DIR=%PROJECT_DIR%\src\main\webapp
set RESOURCES_DIR=%PROJECT_DIR%\src\main\resources
set LIB_DIR=%PROJECT_DIR%\lib
set BUILD_DIR=%PROJECT_DIR%\build
set PROJECT_NAME=bibliotheque
set WAR_FILE=%PROJECT_NAME%.war
set WEBAPPS_DIR=%TOMCAT_DIR%\webapps

echo === Nettoyage des anciens fichiers ===
if exist "%BUILD_DIR%" rd /s /q "%BUILD_DIR%"
if exist "%WEBAPPS_DIR%\%PROJECT_NAME%" rd /s /q "%WEBAPPS_DIR%\%PROJECT_NAME%"
if exist "%WEBAPPS_DIR%\%WAR_FILE%" del /q "%WEBAPPS_DIR%\%WAR_FILE%"
if exist "%WAR_FILE%" del /q "%WAR_FILE%"

echo === Création des dossiers nécessaires ===
mkdir "%BUILD_DIR%\WEB-INF\classes"
mkdir "%BUILD_DIR%\WEB-INF\lib"

echo === Compilation des fichiers Java ===
set FILES=
for /R "%JAVA_DIR%" %%f in (*.java) do (
    set FILES=!FILES! "%%f"
)

javac -cp "%LIB_DIR%\*;%RESOURCES_DIR%" -d "%BUILD_DIR%\WEB-INF\classes" !FILES!

if %errorlevel% neq 0 (
    echo [ERREUR] La compilation a échoué.
    pause
    exit /b %errorlevel%
)

echo === Copie des fichiers JSP et Web ===
xcopy /s /y "%WEBAPP_DIR%\*" "%BUILD_DIR%\" /I > nul

echo === Copie des fichiers Spring / JPA ===
xcopy /s /y "%RESOURCES_DIR%\*" "%BUILD_DIR%\WEB-INF\" > nul

echo === Copie des fichiers .jar ===
xcopy /s /y "%LIB_DIR%\*.jar" "%BUILD_DIR%\WEB-INF\lib" > nul

echo === Création du fichier WAR ===
jar -cvf "%WAR_FILE%" -C "%BUILD_DIR%" . > nul

echo === Copie du fichier WAR vers Tomcat ===
copy /y "%WAR_FILE%" "%WEBAPPS_DIR%\%WAR_FILE%" > nul

echo ✅ Déploiement terminé avec succès !

:: Optionnel : démarrer Tomcat automatiquement
:: start "" "%TOMCAT_DIR%\bin\startup.bat"

pause
