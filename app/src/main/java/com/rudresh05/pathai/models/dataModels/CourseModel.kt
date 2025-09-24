package com.rudresh05.pathai.models.dataModels

class CourseModel(
    var courses: List<Course>
)
class Course(
    var id: String?= null,
    var title: String?=null,
    var content: String?=null,
)