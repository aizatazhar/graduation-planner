package com.example.graduation_planner.models

class SampleModules {
    companion object {
        fun getSampleModules() : List<Module> {
            return listOf(
                    Module("CS1101S", 4, listOf(1, 2)),
                    Module("CS1231S", 4, listOf(1, 2)),
                    Module("CS2030", 4, listOf(1, 2)),
                    Module("CS2040S", 4, listOf(1, 2)),
                    Module("CS2100", 4, listOf(1, 2)),
                    Module("CS2103T", 4, listOf(1, 2)),
                    Module("CS2101", 4, listOf(1, 2)),
            )
        }
    }
}