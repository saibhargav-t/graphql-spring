# GraphQL with Spring Boot Tutorial

A comprehensive tutorial project demonstrating how to build a GraphQL API using Spring Boot, featuring user management with reactive R2DBC and H2 database.

## Table of Contents

- [GraphQL with Spring Boot Tutorial](#graphql-with-spring-boot-tutorial)
  - [Table of Contents](#table-of-contents)
  - [Overview](#overview)
  - [Features](#features)
  - [Tech Stack](#tech-stack)
  - [Prerequisites](#prerequisites)
  - [Getting Started](#getting-started)
    - [Clone the Repository](#clone-the-repository)
    - [Build the Project](#build-the-project)
    - [Run the Application](#run-the-application)
  - [Project Structure](#project-structure)
  - [Database Schema](#database-schema)
    - [Users Table](#users-table)
    - [Address Table](#address-table)
  - [GraphQL API](#graphql-api)
    - [Endpoint](#endpoint)
    - [Sample Queries](#sample-queries)
      - [Get All Users](#get-all-users)
      - [Get User by ID with Address](#get-user-by-id-with-address)
      - [Using Variables](#using-variables)
    - [Sample Mutations](#sample-mutations)
      - [Create a New User](#create-a-new-user)
      - [Using Variables in Mutations](#using-variables-in-mutations)
  - [Documentation](#documentation)
    - [📚 GraphQL.md](#-graphqlmd)
    - [📝 GraphQLQueries.md](#-graphqlqueriesmd)
    - [🔧 schema.graphql](#-schemagraphql)
  - [Learning Path](#learning-path)
  - [What You'll Learn](#what-youll-learn)
  - [Testing the API](#testing-the-api)
    - [Using Altair GraphQL Client (Recommended)](#using-altair-graphql-client-recommended)
    - [Using Postman](#using-postman)
    - [Using cURL](#using-curl)
    - [Using GraphQL Playground](#using-graphql-playground)
  - [Contributing](#contributing)

## Overview

This project is a hands-on tutorial for learning GraphQL with Spring Boot. It implements a user management system with address information, demonstrating core GraphQL concepts including queries, mutations, variables, fragments, and more.

The application uses an in-memory H2 database pre-populated with sample data from Indian mythology (Ramayana characters), making it easy to test and explore without external dependencies.

## Features

- ✅ **GraphQL API** - Full-featured GraphQL server with queries and mutations
- ✅ **Reactive Programming** - Uses Spring Data R2DBC for non-blocking database operations
- ✅ **In-Memory Database** - H2 database with pre-populated sample data
- ✅ **Type Safety** - Strongly-typed GraphQL schema
- ✅ **Nested Queries** - Support for fetching related data (users with addresses)
- ✅ **Enums** - Employment type enumeration
- ✅ **Variables & Fragments** - Advanced GraphQL features demonstrated
- ✅ **REST Endpoints** - Traditional REST API alongside GraphQL

## Tech Stack

| Technology            | Version | Purpose                  |
| --------------------- | ------- | ------------------------ |
| **Java**              | 25      | Programming Language     |
| **Spring Boot**       | 4.0.0   | Application Framework    |
| **GraphQL Java**      | Latest  | GraphQL Implementation   |
| **Spring Data R2DBC** | Latest  | Reactive Database Access |
| **H2 Database**       | Latest  | In-Memory Database       |
| **Lombok**            | Latest  | Reduce Boilerplate Code  |
| **Maven**             | Latest  | Build Tool               |

## Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK) 25** or higher
- **Maven 3.6+** for building the project
- **IDE** (recommended: IntelliJ IDEA, Eclipse, or VS Code with Java extensions)
- **Git** for cloning the repository
- **Altair GraphQL Client** (recommended for testing) or **Postman**/**GraphQL Playground**

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/saibhargav-t/graphql-spring.git
cd graphql-spring
```

### Build the Project

Navigate to the `graphql` directory and build using Maven:

```bash
cd graphql
mvn clean install
```

### Run the Application

```bash
mvn spring-boot:run
```

The application will start on **port 2619**. You should see output indicating the server has started successfully.

**Access Points:**

- GraphQL Endpoint: `http://localhost:2619/graphql`
- H2 Console: `http://localhost:2619/h2-console` (if enabled)

## Project Structure

``` text
graphql-spring/
├── GraphQL.md                          # Comprehensive GraphQL theory documentation
├── graphql/
│   ├── pom.xml                         # Maven dependencies
│   └── src/
│       └── main/
│           ├── java/com/graphql/
│           │   ├── GraphQlApplication.java      # Main application class
│           │   ├── config/
│           │   │   └── Configurations.java      # GraphQL configuration & data fetchers
│           │   ├── constant/
│           │   │   └── Employment.java          # Employment enum
│           │   ├── controller/
│           │   │   └── RestEndpoints.java       # REST API controller
│           │   ├── dao/
│           │   │   ├── UserDAO.java             # User data access interface
│           │   │   ├── AddressRepository.java   # Address repository
│           │   │   └── impl/                    # DAO implementations
│           │   ├── model/
│           │   │   ├── Users.java               # User entity
│           │   │   ├── Address.java             # Address entity
│           │   │   └── QLRequestBody.java       # GraphQL request model
│           │   └── service/
│           │       ├── Services.java            # User service interface
│           │       ├── AddressService.java      # Address service interface
│           │       └── impl/                    # Service implementations
│           └── resources/
│               ├── application.properties       # App configuration
│               ├── schema.graphql               # GraphQL schema definition
│               ├── schema.sql                   # Database schema
│               ├── data.sql                     # Sample data
│               └── GraphQLQueries.md            # Query examples & documentation
```

## Database Schema

The application uses two main tables:

### Users Table

- `id` (VARCHAR) - Primary Key
- `name` (VARCHAR) - User's name
- `email` (VARCHAR) - Email address
- `age` (INT) - User's age
- `gender` (CHAR) - Gender (M/F)
- `employment` (VARCHAR) - Employment type (SALARIED, SELF_EMPLOYED, GOVERNMENT_OFFICIAL)

### Address Table

- `id` (VARCHAR) - Primary Key
- `street` (VARCHAR) - Street address
- `city` (VARCHAR) - City name
- `state` (VARCHAR) - State name
- `country` (VARCHAR) - Country name
- `user_id` (VARCHAR) - Foreign Key to Users

**Sample Data:** The database is pre-populated with 10 users (characters from Ramayana) and their addresses.

## GraphQL API

### Endpoint

**POST** `http://localhost:2619/graphql`

Send GraphQL queries and mutations to this endpoint with a JSON body:

```json
{
  "query": "your GraphQL query here",
  "variables": {}
}
```

### Sample Queries

#### Get All Users

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

#### Get User by ID with Address

```graphql
query GetUserWithAddress {
  getUser(id: "a3f1f510-9c4f-4eac-8f0e-6d1d4c2bcc10") {
    name
    email
    age
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

#### Using Variables

```graphql
query GetUser($userId: String!) {
  getUser(id: $userId) {
    name
    email
  }
}
```

**Variables:**

```json
{
  "userId": "a3f1f510-9c4f-4eac-8f0e-6d1d4c2bcc10"
}
```

### Sample Mutations

#### Create a New User

```graphql
mutation CreateUser {
  createUser(
    name: "New User",
    email: "newuser@example.com",
    age: 25,
    gender: "M",
    employment: SALARIED,
    street: "123 Main St",
    city: "Mumbai",
    state: "Maharashtra",
    country: "India"
  )
}
```

#### Using Variables in Mutations

```graphql
mutation CreateUserWithVariables($name: String!, $email: String!) {
  createUser(
    name: $name,
    email: $email,
    age: 30,
    gender: "F",
    employment: SELF_EMPLOYED,
    street: "456 Park Ave",
    city: "Delhi",
    state: "Delhi",
    country: "India"
  )
}
```

**Variables:**

```json
{
  "name": "Jane Doe",
  "email": "jane@example.com"
}
```

## Documentation

This project includes comprehensive documentation to help you learn:

### 📚 [GraphQL.md](GraphQL.md)

Complete guide covering:

- GraphQL fundamentals (SDL, Runtime, Query Language)
- GraphQL vs REST vs gRPC comparison
- How GraphQL works in Java/Spring Boot
- Request flow diagrams
- Data Fetchers and Schema configuration
- **Glossary** of all GraphQL terms

### 📝 [GraphQLQueries.md](graphql/src/main/resources/GraphQLQueries.md)

Practical examples including:

- Basic queries and mutations
- Using variables
- Fragments for reusable fields
- Aliases for renaming fields
- Real-world query patterns

### 🔧 [schema.graphql](graphql/src/main/resources/schema.graphql)

The GraphQL schema defining:

- Available queries (`getUser`, `getAll`)
- Mutations (`createUser`)
- Types (`Users`, `Address`)
- Enums (`Employment`)

## Learning Path

Follow this recommended path to get the most out of this tutorial:

1. **Start with Theory** 📖
   - Read [GraphQL.md](GraphQL.md) to understand GraphQL fundamentals
   - Pay special attention to the Glossary section

2. **Explore the Schema** 🔍
   - Review [schema.graphql](graphql/src/main/resources/schema.graphql)
   - Understand the type system and available operations

3. **Run the Application** 🚀
   - Build and start the server
   - Verify it's running on port 2619

4. **Try Sample Queries** 🧪
   - Use [GraphQLQueries.md](graphql/src/main/resources/GraphQLQueries.md) as reference
   - Test queries in Postman or GraphQL Playground
   - Experiment with different field selections

5. **Examine the Code** 💻
   - Study `Configurations.java` to see how Data Fetchers work
   - Review the service and DAO layers
   - Understand the reactive R2DBC implementation

6. **Advanced Features** 🎓
   - Try fragments and aliases
   - Use variables in your queries
   - Create custom mutations

7. **Modify and Extend** 🛠️
   - Add new fields to the schema
   - Create new queries or mutations
   - Implement your own data fetchers

## What You'll Learn

By working through this project, you'll gain hands-on experience with:

- ✅ Setting up a GraphQL server with Spring Boot
- ✅ Defining GraphQL schemas using SDL
- ✅ Implementing Data Fetchers for queries and mutations
- ✅ Working with reactive programming (R2DBC)
- ✅ Handling nested object relationships
- ✅ Using GraphQL variables and fragments
- ✅ Configuring in-memory databases
- ✅ Testing GraphQL APIs
- ✅ Understanding GraphQL vs REST differences

## Testing the API

### Using Altair GraphQL Client (Recommended)

[Altair GraphQL Client](https://altairgraphql.dev/) is a beautiful, feature-rich GraphQL client that was used to test this project.

**Installation:**

- Download from [altairgraphql.dev](https://altairgraphql.dev/)
- Available as desktop app (Windows, Mac, Linux) or browser extension

**Setup:**

1. Open Altair GraphQL Client
2. Set the GraphQL endpoint to `http://localhost:2619/graphql`
3. Start writing queries with auto-completion and schema documentation
4. Use the built-in query history and variable editor

**Why Altair?**

- ✨ Beautiful, intuitive interface
- 📝 Auto-completion based on your schema
- 📚 Built-in documentation explorer
- 🔍 Query history and collections
- 🎨 Syntax highlighting and formatting


### Using Postman

1. Create a new POST request to `http://localhost:2619/graphql`
2. Set the body to raw JSON
3. Add your GraphQL query:

```json
{
  "query": "{ getAll { name email } }"
}
```

### Using cURL

```bash
curl -X POST http://localhost:2619/graphql \
  -H "Content-Type: application/json" \
  -d '{"query": "{ getAll { name email } }"}'
```

### Using GraphQL Playground

If you have GraphQL Playground installed, simply point it to `http://localhost:2619/graphql` and start exploring with auto-completion and documentation.

**Note:** While any GraphQL client works, Altair GraphQL Client is recommended for the best developer experience with this project.

## Contributing

Contributions are welcome! If you find issues or have suggestions for improvements:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/improvement`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature/improvement`)
5. Create a Pull Request

---

**Happy Learning! 🚀**

For questions or feedback, please open an issue in the repository.
