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
            var satisfiesGer = false
            var satisfiesGeq = false
            var satisfiesGeh = false
            var satisfiesGet = false
            var satisfiesGes = false

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
            val algorithmsAndTheoryPrimaries: Set<String> = setOf(
                    "CS3230", "CS3236", "CS4231", "CS4232", "CS4234"
            )
            val algorithmsAndTheoryElectives: Set<String> = setOf(
                    "CS3233", "CS4257", "CS4261", "CS4268", "CS4269", "CS4330",
                    "CS5230", "CS5234", "CS5236", "CS5237", "CS5238", "CS5330"
            )
            val artificialIntelligencePrimaries: Set<String> = setOf(
                    "CS3243", "CS3244", "CS4243", "CS4244", "CS4246", "CS4248"
            )
            val artificialIntelligenceElectives: Set<String> = setOf(
                    "CS4220", "CS4261", "CS4269", "CS4277", "CS4278", "CS5215",
                    "CS5228", "CS5242", "CS5260", "CS5340", "CS5339"
            )
            val computerGraphicsAndGamesPrimaries: Set<String> = setOf(
                    "CS3241", "CS3242", "CS3247", "CS4247", "CS4350"
            )
            val computerGraphicsAndGamesElectives: Set<String> = setOf(
                    "CS3218", "CS3240", "CS3249", "CS4240", "CS4243", "CS4249",
                    "CS4351", "CS5237", "CS5240", "CS5343", "CS5346"
            )
            val computerSecurityPrimaries: Set<String> = setOf(
                    "CS2107", "CS3235", "CS4236", "CS4238", "CS4239"
            )
            val computerSecurityElectives: Set<String> = setOf(
                    "CS3211", "CS4257", "CS4276", "CS5231", "CS5250", "CS5321",
                    "CS5322", "CS5331", "CS5332", "IFS4101", "IFS4102", "IFS4103"
            )
            val databaseSystemsPrimaries: Set<String> = setOf(
                    "CS2102", "CS3223", "CS4221", "CS4224", "CS4225"
            )
            val databaseSystemsElectives: Set<String> = setOf(
                    "CS4220", "CS5226", "CS5228", "CS5322"
            )
            val multimediaInformationRetrievalPrimaries: Set<String> = setOf(
                    "CS2108", "CS3245", "CS4242", "CS4248", "CS4347"
            )
            val multimediaInformationRetrievalElectives: Set<String> = setOf(
                    "CS5246", "CS5241"
            )
            val networkingAndDistributedSystemsPrimaries: Set<String> = setOf(
                    "CS2105", "CS3103", "CS422", "CS4226", "CS4231"
            )
            val networkingAndDistributedSystemsElectives: Set<String> = setOf(
                    "CS3237", "CS4344", "CS5223", "CS5224", "CS5229", "CS5248", "CS5321"
            )
            val parallelComputingPrimaries: Set<String> = setOf(
                    "CS3210", "CS3211", "CS4231", "CS4223"
            )
            val parallelComputingElectives: Set<String> = setOf(
                    "CS5222", "CS5223", "CS5224", "CS5239", "CS5250"
            )
            val programmingLanguagesPrimaries: Set<String> = setOf(
                    "CS2104", "CS3211", "CS4212", "CS4215"
            )
            val programmingLanguagesElectives: Set<String> = setOf(
                    "CS3234", "CS4216", "CS5232", "CS5214", "CS5215", "CS5218"
            )
            val softwareEngineeringPrimaries: Set<String> = setOf(
                    "CS2103", "CS2103T", "CS3219", "CS4211", "CS4218", "CS4239"
            )
            val softwareEngineeringElectives: Set<String> = setOf(
                    "CS3216", "CS3217", "CS3226", "CS3234", "CS5219", "CS5232", "CS5272"
            )
            val others: Set<String> = setOf(
                    "CS2220", "CS5233"
            )

            val all: MutableSet<String> = mutableSetOf()
            all.addAll(algorithmsAndTheoryPrimaries)
            all.addAll(algorithmsAndTheoryElectives)
            all.addAll(artificialIntelligencePrimaries)
            all.addAll(artificialIntelligenceElectives)
            all.addAll(computerGraphicsAndGamesPrimaries)
            all.addAll(computerGraphicsAndGamesElectives)
            all.addAll(computerSecurityPrimaries)
            all.addAll(computerSecurityElectives)
            all.addAll(databaseSystemsPrimaries)
            all.addAll(databaseSystemsElectives)
            all.addAll(multimediaInformationRetrievalPrimaries)
            all.addAll(multimediaInformationRetrievalElectives)
            all.addAll(networkingAndDistributedSystemsPrimaries)
            all.addAll(networkingAndDistributedSystemsElectives)
            all.addAll(parallelComputingPrimaries)
            all.addAll(parallelComputingElectives)
            all.addAll(programmingLanguagesPrimaries)
            all.addAll(programmingLanguagesElectives)
            all.addAll(softwareEngineeringPrimaries)
            all.addAll(softwareEngineeringElectives)
            all.addAll(others)

            var totalCredits = 0
            val algorithmsAndTheoryFocusArea: MutableSet<String> = mutableSetOf()
            val artificialIntelligenceFocusArea: MutableSet<String> = mutableSetOf()
            val computerGraphicsAndGamesFocusArea: MutableSet<String> = mutableSetOf()
            val computerSecurityFocusArea: MutableSet<String> = mutableSetOf()
            val databaseSystemsFocusArea: MutableSet<String> = mutableSetOf()
            val multimediaInformationRetrievalFocusArea: MutableSet<String> = mutableSetOf()
            val networkingAndDistributedFocusArea: MutableSet<String> = mutableSetOf()
            val parallelComputingFocusArea: MutableSet<String> = mutableSetOf()
            val programmingLanguagesFocusArea: MutableSet<String> = mutableSetOf()
            val softwareEngineeringFocusArea: MutableSet<String> = mutableSetOf()

            for (module: Module in modules) {
                when (module.moduleCode) {
                    in algorithmsAndTheoryPrimaries -> algorithmsAndTheoryFocusArea.add(module.moduleCode)
                    in artificialIntelligencePrimaries -> artificialIntelligenceFocusArea.add(module.moduleCode)
                    in computerGraphicsAndGamesPrimaries -> computerGraphicsAndGamesFocusArea.add(module.moduleCode)
                    in computerSecurityPrimaries -> computerSecurityFocusArea.add(module.moduleCode)
                    in databaseSystemsPrimaries -> databaseSystemsFocusArea.add(module.moduleCode)
                    in multimediaInformationRetrievalPrimaries -> multimediaInformationRetrievalFocusArea.add(module.moduleCode)
                    in networkingAndDistributedSystemsPrimaries -> networkingAndDistributedFocusArea.add(module.moduleCode)
                    in parallelComputingPrimaries -> parallelComputingFocusArea.add(module.moduleCode)
                    in programmingLanguagesPrimaries -> programmingLanguagesFocusArea.add(module.moduleCode)
                    in softwareEngineeringPrimaries -> softwareEngineeringFocusArea.add(module.moduleCode)
                }

                if (module.moduleCode in all) {
                    totalCredits += module.moduleCredit
                }
            }

            return totalCredits >= 32
                    && (checkFocusArea(algorithmsAndTheoryFocusArea)
                    || checkFocusArea(artificialIntelligenceFocusArea)
                    || checkFocusArea(computerGraphicsAndGamesFocusArea)
                    || checkFocusArea(computerSecurityFocusArea)
                    || checkFocusArea(databaseSystemsFocusArea)
                    || checkFocusArea(multimediaInformationRetrievalFocusArea)
                    || checkFocusArea(networkingAndDistributedFocusArea)
                    || checkFocusArea(parallelComputingFocusArea)
                    || checkFocusArea(programmingLanguagesFocusArea)
                    || checkFocusArea(softwareEngineeringFocusArea))
        }

        private fun checkFocusArea(set: Set<String>): Boolean {
            var atLeastOneLevel4k = false
            for (moduleCode in set) {
                if (moduleCode.toCharArray()[2] == '4') {
                    atLeastOneLevel4k = true
                }
            }

            return set.size >= 3 && atLeastOneLevel4k
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
            var totalCreditsEarned = 0
            for (module: Module in modules) {
                totalCreditsEarned += module.moduleCredit
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