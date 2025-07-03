@echo off
setlocal

:: === CONFIGURATION ===
set TOMCAT_DIR=C:\apache-tomcat-10.1.34
set WEBAPPS_DIR=%TOMCAT_DIR%\webapps
set WAR_NAME=bibliotheque.war
set PROJECT_WAR_PATH=D:\S4\MrNaina\bibliotheque\target\%WAR_NAME%

:: === ÉTAPE 1 : STOP TOMCAT ===
echo [INFO] Arrêt de Tomcat...
call "%TOMCAT_DIR%\bin\shutdown.bat"
timeout /t 5 > nul

:: === ÉTAPE 2 : SUPPRESSION DES FICHIERS EXISTANTS ===
echo [INFO] Suppression de l'ancienne application...
if exist "%WEBAPPS_DIR%\bibliotheque" (
    rd /s /q "%WEBAPPS_DIR%\bibliotheque"
    echo [INFO] Dossier bibliotheque supprimé.
)
if exist "%WEBAPPS_DIR%\%WAR_NAME%" (
    del /q "%WEBAPPS_DIR%\%WAR_NAME%"
    echo [INFO] WAR précédent supprimé.
)

:: === ÉTAPE 3 : COPIE DU NOUVEAU .WAR ===
echo [INFO] Copie du nouveau fichier WAR...
copy /y "%PROJECT_WAR_PATH%" "%WEBAPPS_DIR%"
if errorlevel 1 (
    echo [ERREUR] La copie du WAR a échoué.
    exit /b 1
)
echo [INFO] Copie terminée.

:: === ÉTAPE 4 : DÉMARRAGE TOMCAT ===
echo [INFO] Démarrage de Tomcat...
call "%TOMCAT_DIR%\bin\startup.bat"

echo [INFO] Déploiement terminé avec succès.

endlocal
pause
