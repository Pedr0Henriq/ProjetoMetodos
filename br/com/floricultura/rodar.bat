@echo off
chcp 65001 >nul
echo ================================
echo   FLORICULTURA - Iniciando...
echo ================================

set JAVA="C:\Program Files\Java\jdk-25.0.2\bin\java.exe"
set JAVAC="C:\Program Files\Java\jdk-25.0.2\bin\javac.exe"

echo Compilando o projeto...

if not exist out mkdir out

for /R . %%f in (*.java) do echo %%f >> fontes.txt

%JAVAC% -d out @fontes.txt
del fontes.txt

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo ERRO na compilacao! Verifique os arquivos .java
    pause
    exit /b 1
)

echo Compilacao concluida com sucesso!
echo.
%JAVA% -cp out br.com.floricultura.Main

pause