package com.production.quizharrypotterapp.db

import javax.inject.Inject

class QuestionLocalDataSource @Inject constructor() {

    val listQuestion: List<Question> = listOf(
        Question(
            "What is the name of the second brother in the story of 'The Three Brothers'?",
            listOf("Ignotus Peverell", "Antioch Peverell", "Cadmus Peverell", "Albus Dumbledore"),
            1
        ),
        Question(
            "What is the spell used to destroy the Elder Wand?",
            listOf("Expelliarmus", "Avada Kedavra", "Crucio", "Imperio"),
            0
        ),
        Question(
            "What is the unforgivable curse used by Bellatrix Lestrange against Sirius Black in Harry Potter and the Order of Phoenix?",
            listOf("Avada Kedavra", "Crucio", "Imperio", "Sectumsempra"),
            1
        ),
        Question(
            "What is the name of the mirror that shows a person's deepest desires in Harry Potter and the Goblet of Fire?",
            listOf("Mirror of Oesed", "Mirror of Darkness", "Mirror of Truth", "Mirror of Erised"),
            3
        ),
        Question(
            "What is the name of the spell that Harry uses to try to stop Severus Snape during the Battle of Hogwarts in Harry Potter and the Deathly Hallows?",
            listOf("Expelliarmus", "Stupefy", "Sectumsempra", "Levicorpus"),
            0
        ),
        Question(
            "What is the name of the seer who predicts Harry Potter's death in Harry Potter and the Order of Phoenix?",
            listOf("Sybill Trelawney", "Merope Gaunt", "Bathilda Bagshot", "Irma Pince"),
            0
        ),
        Question(
            "What is the name of the spell used to destroy a Horcrux in Harry Potter and the Deathly Hallows - Part 2?",
            listOf("Avada Kedavra", "Sectumsempra", "Incendio", "Fiendfyre"),
            3
        ),
        Question(
            "What is the name of the wizard who discovers how to create a Philosopher's Stone in Harry Potter and the Philosopher's Stone?",
            listOf("Albus Dumbledore", "Nicolas Flamel", "Gellert Grindelwald", "Armando Dippet"),
            1
        ),
        Question(
            "Which of the following characters is NOT a member of the Order of the Phoenix?",
            listOf("Aberforth Dumbledore", "Gideon Prewett", "Emmeline Vance", "Fabian Prewett"),
            1
        ),
        Question(
            "Who is the author of the book 'A History of Magic' in the world of Harry Potter?",
            listOf("Adalberto Waffling", "Bathilda Bagshot", "Miranda Goshawk", "Phyllida Spore"),
            1
        ),
        Question(
            "What is Voldemort's full name?",
            listOf("Tom Marvolo Riddle", "Tom Percival Riddle", "Tom Marvolo Slytherin", "Tom Lucius Riddle"),
            0
        ),
        Question(
            "Who was the first known owner of the Elder Wand?",
            listOf("Egbert the Egregious", "Hereward", "Antioch Peverell", "Godelot"),
            2
        ),
        Question(
            "What effect does the 'Cruciatus' curse have?",
            listOf("Immobilizes the target", "Creates a protective barrier", "Makes the caster invisible", "Causes intense pain"),
            3
        ),
        Question(
            "What is the main ingredient in Felix Felicis potion?",
            listOf("Snake fangs", "Unicorn blood", "Valerian root", "Phoenix feather"),
            3
        ),
        Question(
            "What object does Harry find in the Room of Requirement during his fifth year at Hogwarts?",
            listOf("The Mirror of Erised", "The Philosopher's Stone", "The Invisibility Cloak", "The Sword of Gryffindor"),
            3
        ),
        Question(
            "What is the incantation for the Killing Curse?",
            listOf("Expelliarmus", "Avada Kedavra", "Crucio", "Imperio"),
            1
        ),
        Question(
            "Which of these characters was NOT a member of the Slug Club?",
            listOf("Draco Malfoy", "Hermione Granger", "Cormac McLaggen", "Blaise Zabini"),
            0
        ),
        Question(
            "What is the name of the portrait guarding the entrance to the Gryffindor common room?",
            listOf("Sir Cadogan", "The Fat Lady", "The Grey Lady", "The Bloody Baron"),
            1
        ),
        Question(
            "What type of dragon does Cedric Diggory face in the Triwizard Tournament?",
            listOf("Hungarian Horntail", "Chinese Fireball", "Swedish Short-Snout", "Welsh Green"),
            2
        ),
        Question(
            "Who killed Dobby the house-elf?",
            listOf("Bellatrix Lestrange", "Lucius Malfoy", "Voldemort", "Peter Pettigrew"),
            1
        ),
        Question(
            "What is the name of the house-elf who serves the Malfoy family?",
            listOf("Winky", "Dobby", "Kreacher", "Hokey"),
            2
        ),
        Question(
            "Which of these spells creates a protective shield?",
            listOf("Sectumsempra", "Stupefy", "Protego", "Riddikulus"),
            2
        ),
        Question(
            "What is the core of Hermione Granger's wand?",
            listOf("Dragon heartstring", "Phoenix feather", "Unicorn hair", "Veela hair"),
            2
        ),
        Question(
            "What is the name of the village where Harry's parents lived and were subsequently killed?",
            listOf("Godric's Hollow", "Little Hangleton", "Ottery St. Catchpole", "Tinworth"),
            1
        ),
        Question(
            "Which of these characters was a member of the original Order of the Phoenix?",
            listOf("Nymphadora Tonks", "Luna Lovegood", "Severus Snape", "Regulus Black"),
            0
        )
    )
}
