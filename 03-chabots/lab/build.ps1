docker build `
    --build-arg AWS_ACCESS_KEY_ID=$Env:AWS_ACCESS_KEY_ID `
    --build-arg AWS_SECRET_ACCESS_KEY=$Env:AWS_SECRET_ACCESS_KEY `
    -t terraform . 