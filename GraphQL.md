# GraphQL

## Table of Contents

- [GraphQL](#graphql)
  - [Table of Contents](#table-of-contents)
  - [Introduction](#introduction)
    - [Important Points](#important-points)
    - [GraphQL is a collection of three things](#graphql-is-a-collection-of-three-things)
      - [Schema Definition Language (SDL)](#schema-definition-language-sdl)
        - [Types](#types)
      - [Runtime Environment](#runtime-environment)
        - [Parsing the GraphQl schema file](#parsing-the-graphql-schema-file)
        - [Executing the operations](#executing-the-operations)
      - [Query Language](#query-language)
    - [Data Fetcher](#data-fetcher)
    - [InMemory Schema](#inmemory-schema)
  - [GraphQL vs REST vs gRPC](#graphql-vs-rest-vs-grpc)
  - [How GraphQL works in Java](#how-graphql-works-in-java)
    - [Schema File Components](#schema-file-components)
    - [Data Fetchers \& Execution](#data-fetchers--execution)
  - [GraphQL Request Flow in Spring Boot](#graphql-request-flow-in-spring-boot)
    - [Request Flow Explanation](#request-flow-explanation)
    - [Key Points](#key-points)

## Introduction

Graph QL is a query language for APIs and a runtime for executing those queries by using a type system you define for your data. In a nutshell, Graph QL is a way to describe the data you want to retrieve from an API, and the API will return only that data.

### Important Points

- Graph QL is a query language for API's not a designing tool for API's.
- A client gets exactly what it wants from an API nothing more than that.
- It provides a complete and understandable description of the data in your API, giving clients the power to ask for exactly what they need.

### GraphQL is a collection of three things

- Schema definition language (SDL)
- A runtime for executing those queries by using a type system you define for your data.
- A query language for APIs

#### Schema Definition Language (SDL)

- SDL is a language for describing the shape of your data. It is a way to define the structure of your data in a way that is both human-readable and machine-readable.
- GraphQL schema is used to expose the functionalities that are available in an application to its users.
- A GraphQL schema contains
  - Types which are similar to classes in Java.
  - Operations which can be performed on these types. Similar to methods in Java.

```text
type Query {
getBook(id:Int):Book #takes id as input and returns a Book object
getBooks:[Book] #returns a list of Book objects
}
type Mutation{
    createBook(name:String, pages:Int):Int #takes name and pages as input and returns an Int
}
type Book{
    id:Int
    name:String
    pages:Int
}
```

##### Types

1. **Query**: Readonly type that defines the entry point for queries. It only returns data from the server.
2. **Mutation**: Writeable type that defines the entry point for mutations. It only modifies data on the server.
3. **Subscription**: Readonly type that defines the entry point for subscriptions. It only subscribes to data on the server.

#### Runtime Environment

##### Parsing the GraphQl schema file

Reading information from the schema file again and again will be inefficient so the runtime environment creates an in memory representation of the schema that  contains all the information defined in the schema file.

##### Executing the operations

A user can use any of the operations that were defined in the schema file. Runtime environment is responsible for
handling the user's request it looks for the operation specified in the request then it uses the in memory schema to check
if that operation exists in the schema or not. If it exists then runtime will execute it and will perform the specified action
like reading or manipulating the data at server.

#### Query Language

- Query language is used by clients to use operations that are defined in the GraphQL schema.
- QL enables a client to select only the required fields from a set of fields.

```query
# Query
{
    getBooks{
        name
    }
}
# This goes to the run time environment and checks if the operation exists in the schema or not.
# If it exists then runtime will execute it and will perform the specified action like reading or manipulating the data at server.

# Response
```json
{
    "getBooks": [
        {
            "name": "Book 1"
        },
        {
            "name": "Book 2"
        }
    ]
}
# We are only receiving name because we only asked for name in the query.
```

### Data Fetcher

- A data fetcher is a callback function.
- It is linked to every query, mutation, and field.
- When a client uses an operation defined in the schema file then runtime environment invokes the data fetcher linked
to that operation in order to perform the specified action.

```java
//getBook(id: Int):Book
public DataFetcher<Book> getBook() {
return environment -> bookRepository. findById(environment.getArgument("id")).map(Function.identity()).orElse(null);
}
//getBooks: [Book]
public DataFetcher<List<Book>> getBooks() {
return environment -> bookRepository.findAll();
}
//createBook(name: String, pages: Int): Int
public DataFetcher<Integer> createBook() {
return environment -> bookRepository.save(new Book(environment.getArgument("name"), environment.getArgument("pages"))) .getId();
}
```

### InMemory Schema

- InMemory schema is a representation of the schema file that is created in the runtime environment.
- It is used to check if the operation exists in the schema or not.
- It is created by parsing the schema file.
- It is used to execute the operations.
- It is used to validate the operations.
- It is used to resolve the operations.
- It is used to resolve the fields.
- It is used to resolve the arguments.
- It is used to resolve the return type.
- It is used to resolve the data fetcher.
- It is used to resolve the data fetcher arguments.
- It is used to resolve the data fetcher return type.
- It is used to resolve the data fetcher arguments.
- GraphQL Schema File + Data Fetchers -> Runtime Environment -> InMemory Schema -> Client.

## GraphQL vs REST vs gRPC

| Feature                            | GraphQL                                                                                                            | REST (Representational State Transfer)                                                             | gRPC (Google Remote Procedure Call)                                                     |
| ---------------------------------- | ------------------------------------------------------------------------------------------------------------------ | -------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- |
| **Definition**                     | A query language, SDL, and runtime environment. Not a framework, but a specification for requesting specific data. | An architectural style guided by constraints like Client-Server, Stateless, and Uniform Interface. | An RPC framework by Google allowing clients to directly call methods on remote servers. |
| **Core Philosophy**                | **Client-driven:** Client specifies exactly what data it needs. Focuses on a data graph.                           | **Resource-driven:** Resources identified by URIs. Operations via HTTP verbs.                      | **Action-driven:** Functions/procedures invoked via client stubs.                       |
| **Protocol & Transport**           | Uses HTTP (mostly POST). Protocol agnostic.                                                                        | Uses HTTP standards (methods, status codes).                                                       | Uses HTTP/2 with binary transport and support for streaming.                            |
| **Data Format**                    | Uses SDL for schemas; responses usually JSON.                                                                      | Flexible formats: JSON, XML, etc.                                                                  | Uses Protocol Buffers (ProtoBuf) for both IDL and message format.                       |
| **Endpoints**                      | Single endpoint; request body contains query.                                                                      | Multiple endpoints/URIs for resources.                                                             | No traditional endpoints; exposes methods/procedures.                                   |
| **Over-fetching / Under-fetching** | Solves over-fetching: client gets exactly what it asks for.                                                        | Prone to over-fetching: returns full resource representation.                                      | Strict types and parameters defined in ProtoBuf.                                        |
| **Performance**                    | Good for complex data fetching, but runtime adds processing.                                                       | Depends on payload; can be chatty with multiple requests.                                          | Very high performance due to binary ProtoBuf and HTTP/2.                                |
| **Browser Support & Caching**      | Caching difficult due to POST + single endpoint.                                                                   | Great caching support with HTTP GET.                                                               | Limited browser support; needs gRPC-Web proxy.                                          |
| **Architecture / Flow**            | Client → GraphQL Runtime → Controller → Service.                                                                   | Client → Controller → Service.                                                                     | Client Stub → Server Stub → Implementation.                                             |
| **When to Use**                    | Low bandwidth environments (mobile), Avoid multiple round-trips, Need flexible response structure                  | • Public APIs, Browser caching, Multiple data formats needed                                       | Internal microservices, High performance/low latency, Bidirectional streaming           |

- **REST** is like a restaurant menu: You order a specific dish (resource) and you get exactly what the kitchen decided goes on that plate. If you want a burger and a side salad, you might have to order them separately (multiple requests) or get a combo meal (fixed resource).
- **GraphQL** is like a buffet or subway station: You look at the options (Schema) and tell the server exactly what you want on your plate. You can ask for just the burger patty and lettuce without the bun, and that is exactly what you get.
- **gRPC** is like a military command: It is highly optimized, strict, and efficient. You give a specific order code (method call) via a secure, high-speed line (HTTP/2), and the action is executed immediately without unnecessary chatter or formatting overhead.

## How GraphQL works in Java

```text
┌─────────────────┐                              ┌─────────────────┐
│  Schema file    │                              │  Data Fetchers  │
└────────┬────────┘                              └────────┬────────┘
         │                                                │
         ▼                                                ▼
┌─────────────────┐                              ┌─────────────────┐
│  SchemaParser   │                              │   TypeWiring    │
└────────┬────────┘                              └────────┬────────┘
         │                                                │
         ▼                                                ▼
┌─────────────────┐                              ┌─────────────────┐
│TypeDefinition   │                              │  RuntimeWiring  │
│    Registry     │                              └────────┬────────┘
└────────┬────────┘                                       │
         │                                                │
         │         ┌─────────────────┐                    │
         └────────►│ SchemaGenerator │◄───────────────────┘
                   └────────┬────────┘
                            │
                            ▼
                   ┌─────────────────┐
                   │  GraphQLSchema  │
                   └────────┬────────┘
                            │
                            ▼
                   ┌─────────────────┐
                   │     GraphQL     │
                   └─────────────────┘
```

**Flow Explanation:**

1. **Schema file** is parsed by **SchemaParser** to extract type definitions
2. **SchemaParser** creates a **TypeDefinitionRegistry** containing all type information that acts as in memory schema
3. **Data Fetchers** are wrapped with **TypeWiring** to link them to specific types/fields
4. **TypeWiring** creates **RuntimeWiring** that maps data fetchers to schema elements
5. **SchemaGenerator** combines **TypeDefinitionRegistry** and **RuntimeWiring** to build the schema
6. **GraphQLSchema** is created as the in-memory representation
7. **GraphQL** instance is created from the schema, ready to execute queries

### Schema File Components

- **SchemaParser**: Reads the schema file (often `.graphqls`) and converts the textual representation into an Abstract Syntax Tree (AST).
- **TypeDefinitionRegistry**: Holds the parsed schema definitions in memory. It understands the types (Objects, Interfaces, Unions) but doesn't know how to fetch data for them yet.
- **GraphQLSchema**: The final, immutable object representing the fully executable schema. It contains both the type system and the wiring needed to execute requests.
- **GraphQL**: The main entry point for executing queries. You instantiate this with the `GraphQLSchema`, and then call its `.execute()` method with a query string.

### Data Fetchers & Execution

- **TypeWiring**: A configuration step that associates specific fields in your schema (like `Query.bookById`) with the Java code (DataFetcher) that retrieves that data.
- **RuntimeWiring**: The complete map binding the static schema types to the dynamic runtime logic (DataFetchers). It tells the engine *how* to resolve every field defined in the registry.
- **SchemaGenerator**: The builder that merges the structural definitions (`TypeDefinitionRegistry`) with the execution logic (`RuntimeWiring`) to produce an executable schema.

## GraphQL Request Flow in Spring Boot

```text
┌────────────┐         ┌────────────┐         ┌────────────┐         ┌────────────┐         ┌────────────┐
│            │         │            │         │            │         │            │         │            │
│   Client   │◄───────►│ Controller │◄───────►│  GraphQL   │◄───────►│    Data    │◄───────►│   DB/API   │
│            │         │            │         │            │         │  Fetcher   │         │            │
└────────────┘         └────────────┘         └────────────┘         └────────────┘         └────────────┘
     │                      │                      │                      │                      │
     │                      │                      │                      │                      │
     │  1. POST Request     │                      │                      │                      │
     │  /graphql            │                      │                      │                      │
     │─────────────────────►│                      │                      │                      │
     │                      │                      │                      │                      │
     │                      │  2. Pass Request     │                      │                      │
     │                      │     Body to          │                      │                      │
     │                      │     GraphQL Object   │                      │                      │
     │                      │─────────────────────►│                      │                      │
     │                      │                      │                      │                      │
     │                      │                      │  3. Execute Data     │                      │
     │                      │                      │     Fetcher          │                      │
     │                      │                      │─────────────────────►│                      │
     │                      │                      │                      │                      │
     │                      │                      │                      │  4. Query DB/API     │
     │                      │                      │                      │─────────────────────►│
     │                      │                      │                      │                      │
     │                      │                      │                      │  5. Return Data      │
     │                      │                      │                      │◄─────────────────────│
     │                      │                      │                      │                      │
     │                      │                      │  6. Return Result    │                      │
     │                      │                      │◄─────────────────────│                      │
     │                      │                      │                      │                      │
     │                      │  7. Return Response  │                      │                      │
     │                      │◄─────────────────────│                      │                      │
     │                      │                      │                      │                      │
     │  8. JSON Response    │                      │                      │                      │
     │◄─────────────────────│                      │                      │                      │
     │                      │                      │                      │                      │
```

### Request Flow Explanation

When a client interacts with a GraphQL API in a Spring Boot application, the following sequence occurs:

1. **Client Request**: The client sends an HTTP POST request to the `/graphql` endpoint with a query in the request body.

2. **Controller Processing**: The Spring Boot controller has a method mapped to the `/graphql` URI path (e.g., `http://localhost:8080/graphql`). This method receives the incoming request and extracts the request body containing the GraphQL query.

3. **GraphQL Object Invocation**: The controller passes the request body to the **GraphQL** object, which is the main entry point for query execution. The GraphQL object performs the following operations:
   - **Fetch the query**: Extracts the query string from the request
   - **Fetch the variables** (if provided): Extracts any query variables
   - **Fetch the operation name** (if provided): Identifies which operation to execute if multiple are defined
   - **Execute the Data Fetcher**: Identifies and invokes the appropriate Data Fetcher associated with the requested query or mutation

4. **Data Fetcher Execution**: The Data Fetcher is a callback function linked to the specific field or operation in the schema. It contains the business logic to retrieve or manipulate data.

5. **Database/API Interaction**: The Data Fetcher interacts with the database (via repositories) or external APIs to fetch or modify the required data.

6. **Result Propagation**: The data flows back through the chain:
   - Database/API returns data to the Data Fetcher
   - Data Fetcher returns the result to the GraphQL object
   - GraphQL object formats the response according to the query structure
   - Controller receives the formatted response

7. **Client Response**: The controller sends the final JSON response back to the client, containing exactly the fields requested in the original query.

### Key Points

- The **Controller** acts as the entry point, typically exposing a single `/graphql` endpoint for all GraphQL operations.
- The **GraphQL object** orchestrates the entire execution process, parsing the query and coordinating Data Fetchers.
- **Data Fetchers** encapsulate the business logic and data access layer, keeping concerns separated.
- The bidirectional arrows in the diagram represent the request-response cycle at each layer.
- Unlike REST APIs with multiple endpoints, GraphQL uses a single endpoint with the query structure determining what data is returned.
