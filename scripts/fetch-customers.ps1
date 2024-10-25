$endpoint = "https://randomuser.me/api/"
$userCount = 1000
$batchSize = 100
$sqlStatements = @()
$idCounter = 1

function Escape-SQLString {
    param (
        [string]$inputString
    )
    return $inputString -replace "'", "''"
}

for ($i = 0; $i -lt $userCount; $i += $batchSize) {
    $url = "${endpoint}?results=${batchSize}"

    Write-Host "Fetching from URL: $url"

    try {
        $response = Invoke-RestMethod -Uri $url

        foreach ($user in $response.results) {
            $id = $idCounter++
            $name = Escape-SQLString "$($user.name.first) $($user.name.last)"
            $address = Escape-SQLString "$($user.location.street.name) $($user.location.street.number), $($user.location.postcode), $($user.location.city)"
            $email = Escape-SQLString $user.email
            $telephone = Escape-SQLString $user.phone

            $sqlStatement = "INSERT INTO Customer(id, name, address, email, telephone) VALUES ('$id', '$name', '$address', '$email', '$telephone');"

            $sqlStatements += $sqlStatement
        }
    } catch {
        Write-Host "Error fetching data: $_"
    }
}

$sqlFilePath = "./quarkus-customer-backend/src/main/resources/import.sql"
[System.IO.File]::WriteAllBytes($sqlFilePath, [System.Text.Encoding]::UTF8.GetBytes($sqlStatements -join "`n"))


Write-Host "SQL script created successfully: $sqlFilePath"
