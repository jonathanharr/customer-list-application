# Set the API endpoint and the number of users to fetch
$endpoint = "https://randomuser.me/api/"
$userCount = 1000
$batchSize = 100

# Initialize an array to hold SQL insert statements
$sqlStatements = @()

# Initialize a counter for unique IDs
$idCounter = 1

# Function to escape SQL strings
function Escape-SQLString {
    param (
        [string]$inputString
    )
    return $inputString -replace "'", "''"  # Escape single quotes
}

# Loop to fetch the required number of users in batches
for ($i = 0; $i -lt $userCount; $i += $batchSize) {
    # Create the API request URL with the specified results
    $url = "${endpoint}?results=${batchSize}"

    # Debugging: Output the generated URL
    Write-Host "Fetching from URL: $url"

    try {
        # Fetch data from the API
        $response = Invoke-RestMethod -Uri $url
        
        # Process each user in the response
        foreach ($user in $response.results) {
            # Extract necessary data and escape it
            $id = $idCounter++  # Generate a unique ID by incrementing the counter
            $name = Escape-SQLString "$($user.name.first) $($user.name.last)"
            $address = Escape-SQLString "$($user.location.street.name) $($user.location.street.number), $($user.location.postcode), $($user.location.city)"
            $email = Escape-SQLString $user.email
            $telephone = Escape-SQLString $user.phone
            
            # Create the SQL insert statement
            $sqlStatement = "INSERT INTO Customer(id, name, address, email, telephone) VALUES ('$id', '$name', '$address', '$email', '$telephone');"
            
            # Add the SQL statement to the array
            $sqlStatements += $sqlStatement
        }
    } catch {
        Write-Host "Error fetching data: $_"
    }
}

# Output the SQL statements to a file
# Output the SQL statements to a file without BOM
$sqlFilePath = "./quarkus-customer-backend/src/main/resources/import.sql"
[System.IO.File]::WriteAllBytes($sqlFilePath, [System.Text.Encoding]::UTF8.GetBytes($sqlStatements -join "`n"))


Write-Host "SQL script created successfully: $sqlFilePath"
