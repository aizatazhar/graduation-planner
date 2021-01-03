package com.example.graduation_planner.models

class GraduationRequirements {
    companion object {
        fun satisfiesGraduationRequirements(modules: List<Module>): Boolean {
            return satisfiesUniversityLevelRequirements(modules)
                    && satisfiesComputerScienceFoundations(modules)
                    && satisfiesComputerScienceBreadthAndDepth(modules)
                    && satisfiesIndustrialExperienceRequirements(modules)
                    && satisfiesItProfessionalism(modules)
                    && satisfiesMathematicsAndSciences(modules)
                    && satisfiesCredits(modules)
        }

        fun satisfiesUniversityLevelRequirements(modules: List<Module>): Boolean {
            var satisfiesGer: Boolean = false;
            var satisfiesGeq: Boolean = false;
            var satisfiesGeh: Boolean = false;
            var satisfiesGet: Boolean = false;
            var satisfiesGes: Boolean = false;

            for (module: Module in modules) {
                when (module.moduleCode.substring(0, 3)) {
                    "GER" -> satisfiesGer = true
                    "GEQ" -> satisfiesGeq = true
                    "GEH" -> satisfiesGeh = true
                    "GET" -> satisfiesGet = true
                    "GES" -> satisfiesGes = true
                    else -> continue
                }
            }

            return satisfiesGer && satisfiesGeq && satisfiesGeh && satisfiesGet && satisfiesGes
        }

        fun satisfiesComputerScienceFoundations(modules: List<Module>): Boolean {
            val requiredModuleCodes: List<String> = listOf(
                    "CS1101S", "CS1231S", "CS2030", "CS2040S", "CS2100", "CS2103T",
                    "CS2105", "CS2106", "CS3230"
            )
            val completedModuleCodes: List<String> = toListOfModuleCodes(modules)

            return completedModuleCodes.containsAll(requiredModuleCodes)
        }

        fun satisfiesComputerScienceBreadthAndDepth(modules: List<Module>): Boolean {
            // TODO
            return true
        }

        fun satisfiesIndustrialExperienceRequirements(modules: List<Module>): Boolean {
            val atapModuleCodes: List<String> = listOf("CP3880")
            val sipModuleCodes: List<String> = listOf("CP3200", "CP3202", "CP3107", "CP3110")
            val iipModuleCodes: List<String> = listOf("IS4010")
            val nocModuleCodes: List<String> = listOf("TR3202")
            val fypModuleCodes: List<String> = listOf("CP4101")
            val completedModuleCodes: List<String> = toListOfModuleCodes(modules)

            return completedModuleCodes.containsAll(atapModuleCodes)
                    || completedModuleCodes.containsAll(iipModuleCodes)
                    || completedModuleCodes.containsAll(nocModuleCodes)
                    || completedModuleCodes.containsAll(fypModuleCodes)
                    || completedModuleCodes.filter { moduleCode -> sipModuleCodes.contains(moduleCode) }
                            .size >= 2 // need to take at least 2 modules from SIP modules
        }

        fun satisfiesItProfessionalism(modules: List<Module>): Boolean {
            val requiredModuleCodes: List<String> = listOf(
                    "IS1103", "CS2101", "ES2660"
            )
            val completedModuleCodes: List<String> = toListOfModuleCodes(modules)

            return completedModuleCodes.containsAll(requiredModuleCodes)
        }

        fun satisfiesMathematicsAndSciences(modules: List<Module>): Boolean {
            val compulsoryModuleCodes: List<String> = listOf(
                    "MA1521", "ST2334", "MA1101R"
            )
            val scienceModules: List<String> = listOf(
                    "CM1121",  "CM1131",  "CM1417",  "LSM1102", "LSM1105", "LSM1106",
                    "LSM1301", "LSM1302", "PC1141",  "PC1142",  "PC1143",  "PC1144",
                    "PC1221",  "PC1222",  "PC1432",  "MA2213",  "MA2214",  "CM1101",
                    "CM1111",  "CM1161",  "CM1191",  "CM1401",  "CM1402",  "CM1501",
                    "CM1502",  "LSM1303", "LSM1306", "PC1421",  "PC1431",  "PC1433",
                    "MA1104",  "MA2104",  "MA2101",  "MA2108",  "MA2501",  "ST2132",
                    "ST2137"
            )
            val completedModuleCodes: List<String> = toListOfModuleCodes(modules)

            return completedModuleCodes.containsAll(compulsoryModuleCodes)
                    && completedModuleCodes.any { scienceModules.contains(it) }
        }

        fun satisfiesCredits(modules: List<Module>): Boolean {
            var totalCreditsEarned: Int = 0
            for (module: Module in modules) {
                totalCreditsEarned += module.credits
            }

            return totalCreditsEarned >= 160
        }

        private fun toListOfModuleCodes(modules: List<Module>): List<String> {
            val moduleCodes: MutableList<String> = mutableListOf()
            for (module: Module in modules) {
                moduleCodes.add(module.moduleCode)
            }
            return moduleCodes
        }
    }
}