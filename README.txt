
Small app for testing Spring-Security in Spring Boot

username: string
password: string

Password is encrypted by BCryptPasswordEncoder

Bug: Authentication via AuthenticationApiController is being performed but the user is not being authenticated.
The user is authenticated only when the user is redirected to the login page and logs in there.
But not being authenticated when the user logs in via the API.
