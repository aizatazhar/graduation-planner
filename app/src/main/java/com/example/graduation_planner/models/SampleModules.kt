package com.example.graduation_planner.models

class SampleModules {
    companion object {
        fun getSampleModules() : List<Module> {
            return listOf(
                Module("CS1101S", listOf(1, 2)),
                Module("CS1231S", listOf(1, 2)),
                Module("CS2030", listOf(1, 2)),
                Module("CS2040S", listOf(1, 2)),
                Module("CS2100", listOf(1, 2)),
                Module("CS2103T", listOf(1, 2)),
                Module("CS2101", listOf(1, 2)),
            )
        }
    }
}