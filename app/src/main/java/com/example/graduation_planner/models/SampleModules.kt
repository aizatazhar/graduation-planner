package com.example.graduation_planner.models

class SampleModules {
    companion object {
        fun getSampleModules() : List<Module> {
            return listOf(
                    // Y1S1
                    Module("CS1101S", 4),
                    Module("CS1231S", 4),
                    Module("MA1521", 4),
                    Module("GEH1043", 4),
                    Module("IS1103", 4),

                    // Y1S2
                    Module("CS2030", 4),
                    Module("CS2040S", 4),
                    Module("MA1101R", 4),
                    Module("LSM1106", 4),
                    Module("GER1000", 4),
                    Module("GEQ1000", 4),

                    // Y2S1
                    Module("CS2100", 4),
                    Module("CS2103T", 4),
                    Module("CS2101", 4),
                    Module("ST2334", 4),
                    Module("CP2106", 4),
                    Module("CS1010R", 1),
                    Module("CFG1002", 2),
                    Module("DMX1301", 3),

                    // Y3S1
                    Module("CS3203", 8),
                    Module("CS3230", 4),
                    Module("CS2106", 4),
                    Module("CS2105", 4),
                    Module("GET1020", 4),
                    Module("CP3200", 6),

                    // Y3S2
                    Module("CS4231", 4),
                    Module("CS3211", 4),
                    Module("CS3244", 4),
                    Module("CS2102", 4),
                    Module("GES1035", 4),

                    // Y4S1
                    Module("CS4234", 4),
                    Module("CS3210", 4),
                    Module("CS4243", 4),
                    Module("CS2107", 4),
                    Module("ES2660", 4),

                    // Y4S2
                    Module("CS4222", 4),
                    Module("Some UE", 4),
                    Module("Some UE", 4),
                    Module("DMX1201", 2),
                    Module("CP3202", 6),
            )
        }
    }
}