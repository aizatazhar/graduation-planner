package com.example.graduation_planner.models

class SampleModules {
    companion object {
        fun getSampleModules() : List<Module> {
            return listOf(
                    // Y1S1
                    Module("CS1101S", moduleCredit = 4),
                    Module("CS1231S", moduleCredit = 44),
                    Module("MA1521", moduleCredit = 4),
                    Module("GEH1043", moduleCredit = 4),
                    Module("IS1103", moduleCredit = 4),

                    // Y1S2
                    Module("CS2030", moduleCredit = 4),
                    Module("CS2040S", moduleCredit = 4),
                    Module("MA1101R", moduleCredit = 4),
                    Module("LSM1106", moduleCredit = 4),
                    Module("GER1000", moduleCredit = 4),
                    Module("GEQ1000", moduleCredit = 4),

                    // Y2S1
                    Module("CS2100", moduleCredit = 4),
                    Module("CS2103T", moduleCredit = 4),
                    Module("CS2101", moduleCredit = 4),
                    Module("ST2334", moduleCredit = 4),
                    Module("CP2106", moduleCredit = 4),
                    Module("CS1010R", moduleCredit = 1),
                    Module("CFG1002", moduleCredit = 2),
                    Module("DMX1301", moduleCredit = 3),

                    // Y3S1
                    Module("CS3203", moduleCredit = 8),
                    Module("CS3230", moduleCredit = 4),
                    Module("CS2106", moduleCredit = 4),
                    Module("CS2105", moduleCredit = 4),
                    Module("GET1020", moduleCredit = 4),
                    Module("CP3200", moduleCredit = 6),

                    // Y3S2
                    Module("CS4231", moduleCredit = 4),
                    Module("CS3211", moduleCredit = 4),
                    Module("CS3244", moduleCredit = 4),
                    Module("CS2102", moduleCredit = 4),
                    Module("GES1035", moduleCredit = 4),

                    // Y4S1
                    Module("CS4234", moduleCredit = 4),
                    Module("CS3210", moduleCredit = 4),
                    Module("CS4243", moduleCredit = 4),
                    Module("CS2107", moduleCredit = 4),
                    Module("ES2660", moduleCredit = 4),

                    // Y4S2
                    Module("CS4222", moduleCredit = 4),
                    Module("Some UE", moduleCredit = 4),
                    Module("Some UE", moduleCredit = 4),
                    Module("DMX1201", moduleCredit = 2),
                    Module("CP3202", moduleCredit = 6),
            )
        }
    }
}