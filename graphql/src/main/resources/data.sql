delete from address;

delete from users;

INSERT INTO
    users (
        id,
        name,
        email,
        age,
        gender,
        employment
    )
VALUES (
        'a3f1f510-9c4f-4eac-8f0e-6d1d4c2bcc10',
        'Rama',
        'rama@ayodhya.gov',
        32,
        'M',
        'government_official'
    ),
    (
        'b74c2e11-b55c-4fd2-b39c-126765c670c2',
        'Sita',
        'sita@mithila.in',
        28,
        'F',
        'self_employed'
    ),
    (
        'c91e75e6-ddc7-4cd6-b0c7-b5e9a0bdba01',
        'Lakshmana',
        'lakshmana@ayodhya.gov',
        30,
        'M',
        'government_official'
    ),
    (
        'd1200c41-9ea3-4f4b-9b93-784b4ea77162',
        'Bharata',
        'bharata@ayodhya.gov',
        29,
        'M',
        'government_official'
    ),
    (
        'e4416d87-4933-4acf-a2b7-9fd721fdf134',
        'Shatrughna',
        'shatrughna@ayodhya.gov',
        27,
        'M',
        'government_official'
    ),
    (
        'f7a9f430-8a57-4ce6-a7f1-9d7aa0549af2',
        'Hanuman',
        'hanuman@kishkindha.in',
        35,
        'M',
        'salaried'
    ),
    (
        '0a4fa478-9cd7-4a8d-a1c4-1b12e33b9c91',
        'Sugriva',
        'sugriva@kishkindha.in',
        40,
        'M',
        'self_employed'
    ),
    (
        '1bfc1131-dca0-49f1-acf9-e4ffd39a2ef2',
        'Vibhishana',
        'vibhishana@lanka.gov',
        38,
        'M',
        'government_official'
    ),
    (
        '264cae0e-2f83-4f84-8223-f7bb0ddc65fe',
        'Jambavantha',
        'jambavantha@vanara.in',
        70,
        'M',
        'self_employed'
    ),
    (
        '3c7f936a-a3ff-49ee-ae0c-65b78bf3389e',
        'Indrajit',
        'indrajit@lanka.gov',
        25,
        'M',
        'government_official'
    );

INSERT INTO
    address (
        id,
        street,
        city,
        state,
        country,
        user_id
    )
VALUES (
        '7b81b1e4-5ce2-4ef0-9acb-d0ef9871e10e',
        'Royal Palace Road',
        'Ayodhya',
        'UP',
        'India',
        'a3f1f510-9c4f-4eac-8f0e-6d1d4c2bcc10'
    ), -- Rama
    (
        '8134e2f4-0ea1-4f61-878c-99a3bd61fe2f',
        'Main Palace Street',
        'Mithila',
        'Bihar',
        'India',
        'b74c2e11-b55c-4fd2-b39c-126765c670c2'
    ), -- Sita
    (
        '92c49302-7691-4161-987e-41389b8f00d0',
        'Sarayu Riverside Path',
        'Ayodhya',
        'UP',
        'India',
        'c91e75e6-ddc7-4cd6-b0c7-b5e9a0bdba01'
    ), -- Lakshmana
    (
        'a61f44bd-35f6-4e2e-b2a1-5b1f4c2c9e87',
        'King Dasharatha Marg',
        'Ayodhya',
        'UP',
        'India',
        'd1200c41-9ea3-4f4b-9b93-784b4ea77162'
    ), -- Bharata
    (
        'b4fd094b-7452-4a29-9c83-5816fd1d43f2',
        'Shringi Rishi Road',
        'Ayodhya',
        'UP',
        'India',
        'e4416d87-4933-4acf-a2b7-9fd721fdf134'
    ), -- Shatrughna
    (
        'c1f67b37-6471-4434-9f0f-eebc74c055d9',
        'Mountaintop Cave Path',
        'Kishkindha',
        'Karnataka',
        'India',
        'f7a9f430-8a57-4ce6-a7f1-9d7aa0549af2'
    ), -- Hanuman
    (
        'd3b74b84-8507-4c22-ac43-a9fdce9ce441',
        'Vanara Market Road',
        'Kishkindha',
        'Karnataka',
        'India',
        '0a4fa478-9cd7-4a8d-a1c4-1b12e33b9c91'
    ), -- Sugriva
    (
        'e9d9235f-300a-4500-9cd1-8fd8ed7ac9da',
        'Lanka Fortress Lane',
        'Lanka',
        'Tamil Nadu',
        'India',
        '1bfc1131-dca0-49f1-acf9-e4ffd39a2ef2'
    ), -- Vibhishana
    (
        'f55673e3-0372-4b3f-9fc1-198cbb69adf8',
        'Mountain Forest Trail',
        'Dandakaranya',
        'Chhattisgarh',
        'India',
        '264cae0e-2f83-4f84-8223-f7bb0ddc65fe'
    ), -- Jambavantha
    (
        '0ea57c61-bce4-4ba3-9a55-336ff78f6090',
        'Golden Palace Road',
        'Lanka',
        'Tamil Nadu',
        'India',
        '3c7f936a-a3ff-49ee-ae0c-65b78bf3389e'
    );
-- Indrajit