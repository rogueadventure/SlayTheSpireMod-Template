@echo off
setlocal

REM ============================================================
REM BUILD.BAT – COMPILADOR SIMPLE DEL MOD
REM ============================================================
REM Este script:
REM  1) Compila los .java
REM  2) Genera el .jar del mod
REM  3) Incluye ModTheSpire.json dentro del jar
REM
REM REQUISITO: tener instalado JDK 8 y javac en PATH
REM ============================================================

REM Ruta raíz del mod
set ROOT=%~dp0

REM Carpeta temporal donde se compilan las clases
set OUT=%ROOT%out

REM Librerías necesarias del juego
set JARS=%ROOT%..\desktop-1.0.jar;%ROOT%..\BaseMod.jar;%ROOT%..\StSLib.jar;%ROOT%..\ModTheSpire.jar

REM Limpiar compilaciones anteriores
if exist "%OUT%" rmdir /s /q "%OUT%"
mkdir "%OUT%"

REM ============================================================
REM COMPILAR TODAS LAS CLASES DEL MOD
REM Añade aquí nuevos archivos .java cuando crees cartas/patches
REM ============================================================
javac -source 1.8 -target 1.8 -encoding UTF-8 ^
  -cp "%JARS%" ^
  -d "%OUT%" ^
  "%ROOT%src\miprimermod\MiPrimerMod.java" ^
  "%ROOT%src\miprimermod\cards\Bonk.java" ^
  "%ROOT%src\miprimermod\cards\Draw3.java" ^
  "%ROOT%src\miprimermod\patches\StarterDeckPatch.java"

IF ERRORLEVEL 1 (
  echo.
  echo ERROR: fallo compilando
  exit /b 1
)

REM --- Crear JAR del mod ---
if exist "%ROOT%MiPrimerMod.jar" del "%ROOT%MiPrimerMod.jar"

REM 1) Crear jar con las clases compiladas
jar cf "%ROOT%MiPrimerMod.jar" -C "%OUT%" .
IF ERRORLEVEL 1 (
  echo [ERROR] Fallo creando el jar con las clases.
  exit /b 1
)

REM 2) Meter ModTheSpire.json en la raiz del jar
jar uf "%ROOT%MiPrimerMod.jar" -C "%ROOT%src" ModTheSpire.json
IF ERRORLEVEL 1 (
  echo [ERROR] Fallo metiendo ModTheSpire.json.
  exit /b 1
)

REM 3) Meter CONTENIDO de resources en la raiz del jar (clave para las rutas de imagen)
if exist "%ROOT%resources" (
  jar uf "%ROOT%MiPrimerMod.jar" -C "%ROOT%resources" .
) else (
  echo [WARNING] No existe la carpeta resources.
)


echo.
echo [OK] Compilado! JAR generado en: %ROOT%MiPrimerMod.jar
pause
