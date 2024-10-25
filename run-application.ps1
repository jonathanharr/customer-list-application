./scripts/fetch-customers.ps1

Write-Host "Simple wait to let the import.sql file update first"
Start-Sleep -Seconds 2
Write-Host "Composing the docker containers.."

docker-compose -f docker-compose.yml up --build
