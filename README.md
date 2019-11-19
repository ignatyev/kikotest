**Develop a flat viewing scheduler.**

There are 2 parties to the service:
New Tenants who are moving in

Current Tenant who lives in a flat

**Service requirements:**
Flat has 20-minute viewing slots from 10:00 to 20:00 for the upcoming week
New Tenant should be able to reserve viewing. Once reserved slot can not be occupied by other tenants.
Current Tenant should be notified about reservation in at least 24 hours and can either approve or reject it. 

Once rejected this slot can not be reserved by anyone else at any point.
New Tenant gets notified about approval or rejection.
New Tenant can cancel the viewing at any point, current tenant should be notified and viewing slot becomes vacant.

**Constraints:**
Kotlin/Java as a language
Make service available over HTTP
Use minimal approach to the task, no need to use DB/authentication
Please don’t use big frameworks such as Spring(boot), you can use web server frameworks such as undertow, vertx, ktor, netty.
Use stubs for notifications
