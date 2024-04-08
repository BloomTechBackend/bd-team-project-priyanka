# [team name] Design Document

## Instructions

*Save a copy of this template for your team in the same folder that contains
this template.*

*Replace italicized text (including this text!) with details of the design you
are proposing for your team project. (Your replacement text shouldn't be in
italics)*

*You should take a look at the example design document in the same folder as
this template for more guidance on the types of information to capture, and the
level of detail to aim for.*

## HealthMate Design

## 1. Problem Statement

*In rural areas, residents face significant challenges when seeking healthcare services, particularly when it comes to booking hospital appointments. Many individuals from rural areas are compelled to travel long distances to urban centers solely for this purpose. This inconvenience arises from a lack of awareness regarding doctor availability and lack
of information regarding which hospital can provide optimal treatment. As a result, patients often find themselves navigating a confusing and time-consuming process, leading to delays in receiving essential medical attention.*


## 2. Top Questions to Resolve in Review

*List the most important questions you have about your design, or things that
you are still debating internally that you might like help working through.*

1. To solve the problem,I have designed to get the nearby doctors across all the hospitals based on pincode. Should I implement more complicate design to fetch nearby doctors?
2. To get the availability of doctors,I am dividing a day into 15minute framework. I am storing availability of doctors on day basis in a different table in database. Is there any better way to figure out availability of doctor on a particular date?

## 3. Use Cases

*This is where we work backwards from the customer and define what our customers
would like to do (and why). You may also include use cases for yourselves, or
for the organization providing the product to customers.*

U1. *As a user, I can see the availability of doctors on a particular date across all hospitals based on pincode.*

U2. *As a user, I can book appointment for a specific doctor based on the availability.*

U3. *As a user, I can cancel the appointment.*

U4. *As a user, I can see the appointment details.*


## 4. Project Scope

### 4.1. In Scope

* Getting the availability of doctors across all hospitals present in a particular pincode.
* Book and cancel an appointment.

### 4.2. Out of Scope

* Getting availability of nearby doctors based on GPS location.

# 5. Proposed Architecture Overview

*Describe broadly how you are proposing to solve for the requirements you
described in Section 3.*

*This may include class diagram(s) showing what components you are planning to
build.*

*You should argue why this architecture (organization of components) is
reasonable. That is, why it represents a good data flow and a good separation of
concerns. Where applicable, argue why this architecture satisfies the stated
requirements.*

# 6. API

## 6.1. Public Models

*Define the data models your service will expose in its responses via your
*`-Model`* package. These will be equivalent to the *`PlaylistModel`* and
*`SongModel`* from the Unit 3 project.*

## 6.2. *Get Nearby Available Doctors*

* Accepts `GET` requests to `/doctors/pincode?department?date`
* pincode, department, date : Required Parameter
* Accepts current location pincode and show all the available doctors on the particular date across all hospitals based on pincode.
    * If no pincode is given, will throw a
      `InvalidInputException`
## 6.2. *Get Hospital Details by hospital id*

* Accepts `GET` requests to `/hospitals/id
* id : Required Parameter
* Accepts the hospital id and show the details of hospitals for the given hospital id.
    * If no hospital is found, will throw a
      `NoHospitalFoundException`

## 6.3 *Book an Appointment*

* Accepts `POST` requests to `/createappointment`
* Accepts data to create a new appointment with a given userid, doctorid, date.
  Returns a new appointment with unique id.
   * If doctorid is not found, will throw a
     `DoctornotfoundException`
   * If userid is not found, will throw a 
     `Usernotfoundexception`
## 6.3 *Get Appointment Details*

* Accepts `GET` requests to `appointmentid/userid`
* Accepts appointment id and show the details of appointment for a particular user.
    * If appointmentid is not found, will throw a
      `AppointmentnotfoundException`
    * If userid is not found, will throw a
      `Usernotfoundexception`
## 6.4 *Cancel an appointment*

* Accepts `DELETE` requests to `/appointment/id`
* id : Required parameter
* Accepts id and delete the appointment from database.
  * If id is not found, will throw a
     `AppointmentnotfoundException`

*(repeat, but you can use shorthand here, indicating what is different, likely
primarily the data in/out and error conditions. If the sequence diagram is
nearly identical, you can say in a few words how it is the same/different from
the first endpoint)*

# 7. Tables

### 7.1. `users`

```
userid // partition key, string
firstname // string
lastname // string
contact // string
user status // string
```
### 7.2. `hospitals`

```
id // partition key, string
name // string
doctorid // string
contact // string
pincode // string
```
### 7.3. `doctors`

```
id // partition key, string
name // string
department // string
contact // string
availability // string
hospitalid // string
```
### 7.3. `appointments`

```
id // sort key, string
userid // partotion key, string
doctorid // string
appointmentdate // string
hospitalid // string
status // string
```

# 8. Pages

*Include mock-ups of the web pages you expect to build. These can be as
sophisticated as mockups/wireframes using drawing software, or as simple as
hand-drawn pictures that represent the key customer-facing components of the
pages. It should be clear what the interactions will be on the page, especially
where customers enter and submit data. You may want to accompany the mockups
with some description of behaviors of the page (e.g. “When customer submits the
submit-dog-photo button, the customer is sent to the doggie detail page”)*
