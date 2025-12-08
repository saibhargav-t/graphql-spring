# GraphQL Queries and Mutations

This document provides a collection of GraphQL queries and mutations available in the application. It serves as a reference for interacting with the GraphQL API.

## Queries

### 1. Get All Users

Fetch a list of all users with their basic details.

```graphql
query GetAllUsers {
  getAll {
    id
    name
    email
    age
    gender
  }
}
```

### 2. Get User by ID

Fetch a specific user by their unique ID.

```graphql
query GetUserById {
  getUser(id: "dbdf6e1e-8933-462d-a617-edd4dda69b97") {
    name
    email
    age
    gender
  }
}
```

### 3. Get User with Nested Fields

Fetch a user along with their complex nested fields like `address` and `employment`.

```graphql
query GetUserWithDetails {
  getUser(id: "dbdf6e1e-8933-462d-a617-edd4dda69b97") {
    name
    email
    address {
      street
      city
      state
      country
    }
    employment
  }
}
```

## Mutations

### 1. Create User (Hardcoded)

Create a new user with hardcoded values.

```graphql
mutation CreateUser {
  createUser(
    name: "Bhargav",
    email: "bhargav@gmail.com",
    age: 20,
    gender: "M",
    employment: SALARIED,
    street: "Rotary Nagar",
    city: "Khammam",
    state: "Telangana",
    country: "India"
  )
}
```

### 2. Create User (Using Variables)

Create a new user using variables. This is the recommended approach for dynamic data.

**Query:**

```graphql
mutation CreateUserWithVariables($name: String, $gender: String = "M") {
  createUser(
    name: $name,
    email: "bhargav@gmail.com",
    age: 20,
    gender: $gender,
    employment: SALARIED,
    street: "Rotary Nagar",
    city: "Khammam",
    state: "Telangana",
    country: "India"
  )
}
```

**Variables:**

```json
{
  "name": "Bhargav",
  "gender": "M"
}
```

## Advanced Concepts

### 1. Fragments

Fragments let you construct sets of fields, and then include them in queries where you need to.

```graphql
fragment UserDetails on Users {
  id
  name
  email
}

query GetAllUsersWithFragment {
  getAll {
    ...UserDetails
    age
  }
}
```

### 2. Aliases

Aliases allow you to rename the result of a field to anything you want.

```graphql
query GetUserAlias {
  userInfo: getUser(id: "dbdf6e1e-8933-462d-a617-edd4dda69b97") {
    fullName: name
    contactEmail: email
  }
}
```
