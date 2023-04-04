import java.util.Scanner;
import java.util.Random;
import java.util.Hashtable;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class TypeTest {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("\nWelcome to English 1B Terms Typing Test");
        System.out.println("Created by Edouard Gotthardt, March 2023,");
        System.out.println("to help with preparing for the midterm.\n");

        String goAgain = "y";
        
        while (goAgain.equals("y")) {
            countDown();

            String toType = getSentence();

            // Chooses random Term to be typed and prints to screen
            System.out.println(toType);
            System.out.println();

            // Starts timer
            double start = System.currentTimeMillis();

            // User input
            String userTypes = in.nextLine();
            System.out.println();

            // Ends timer
            double end = System.currentTimeMillis();

            // Converts timer to how long it took the user to type in seconds.
            double timeToType = (end - start) / 1000;

            double wpm = calculateWpm(timeToType, toType.length());
            int errors = calculateAccuracy(toType, userTypes);

            System.out.printf("You typed %.2f wpm in a time of " + timeToType + " seconds.\nYou made a total of %d mistakes", wpm, errors);

            System.out.print("\n\nWould you like to go again? y/n: ");
            goAgain = in.nextLine();
            System.out.println();
        }
        
    }   // End of main()

    public static void countDown() {
        System.out.println("Ready.");
            // Spaces out the starting words by 1 second.
            try {
                for(int i = 0; i < 1; i++) {
                    Thread.sleep(1000);
                }
            }
            catch (Exception e) {
                System.out.println("## Error with 'Thread.sleep()' ##");
            }
            System.out.println("Set.");
            try {
                for(int i = 0; i < 1; i++) {
                    Thread.sleep(1000);
                }
            }
            catch (Exception e) {
                System.out.println("## Error with 'Thread.sleep()' ##");
            }
            System.out.println("Go!");
            try {
                for(int i = 0; i < 1; i++) {
                    Thread.sleep(250);
                }
            }
            catch (Exception e) {
                System.out.println("## Error with 'Thread.sleep()' ##");
            }
            System.out.println();
    }

    public static double calculateWpm(double timeToType, double lengthOfString) {
        // avg number of words in sentence divided by the time it took to type in minutes (total seconds divided by 60 seconds)
        return (lengthOfString / 5) / (timeToType / 60);
    }   // End calculateWpm()

    public static int calculateAccuracy(String toType, String userTypes) {
        int totalErrors = 0, mistypeLoc = 0;
        String[] initialSentence = new String[toType.split(" ").length];
        String[] userSentence = new String[userTypes.split(" ").length];
        String[] mistypedWords = new String[toType.split(" ").length];

        initialSentence = toType.split(" ");
        userSentence = userTypes.split(" ");

        int wordCount = initialSentence.length;

        for (int i = 0; i < wordCount; i++) {
            // i will dictate the word being analyzed
            if((userSentence[i] != initialSentence[i]) && (userSentence[i].length() == initialSentence[i].length())) {
                // x will be the letters in each word as i is the word itself
                for (int x = 0; x < initialSentence[i].length(); x++) {
                    if (initialSentence[i].charAt(x) != userSentence[i].charAt(x)) {
                        totalErrors++;
                    }
                    if ((initialSentence[i].charAt(x) != userSentence[i].charAt(x)) && (userSentence[i] != mistypedWords[mistypeLoc])) {
                        mistypedWords[mistypeLoc] = userSentence[i];
                        mistypeLoc ++;
                    }
                }
            }
            // If the user word is not of equal size to the initial word then this will run
            // This is to prevent an out of bounds index call.
            else if (userSentence[i] != initialSentence[i] && userSentence[i].length() != initialSentence[i].length()) {
                // This will run if the user word is larger.
                if (userSentence[i].length() > initialSentence[i].length()) {
                    for (int x = 0; x < initialSentence[i].length(); x++) {
                        if (initialSentence[i].charAt(x) != userSentence[i].charAt(x)) {
                            totalErrors++;
                        }

                        // PROBLEM CHILD Constatly adds mistyped words for number of letters left in word once mistyped letter is found
                        // pull this statement out of the for loop, somehow grasp 'x' when outside of for loop. or put in ANOTHER conditional
                        // to check that there is no instance of the mistyped word anywhere in mystpedWords already.
                        // How many if/else if statements are too man? this seems excessive, as if i want to write a function for a function.
                        if ((initialSentence[i].charAt(x) != userSentence[i].charAt(x)) && (userSentence[i] != mistypedWords[mistypeLoc])) {
                            mistypedWords[mistypeLoc] = userSentence[i];
                            mistypeLoc ++;
                        }
                    }
                    totalErrors += userSentence[i].length() - initialSentence[i].length();
                }
                // This will run if the user word is smaller
                else if (userSentence[i].length() < initialSentence[i].length()) {
                    for (int x = 0; x < userSentence[i].length(); x++) {
                        if (initialSentence[i].charAt(x) != userSentence[i].charAt(x)) {
                            totalErrors++;
                        }
                        if ((initialSentence[i].charAt(x) != userSentence[i].charAt(x)) && (userSentence[i] != mistypedWords[mistypeLoc])) {
                            mistypedWords[mistypeLoc] = userSentence[i];
                            mistypeLoc++;
                        }
                    }
                    totalErrors += initialSentence[i].length() - userSentence[i].length();
                }
            }
        }
        System.out.println("The words you typed incorrectly are: ");
        for (String word : mistypedWords) {
            if (word != null) {
                System.out.print(word + ", ");
            }
            else {
                break;
            }
        }
        return totalErrors;
    }   // End of calculateAccuracy()

    public static String getSentence() {
        Hashtable<String, String> termsList = new Hashtable<String, String>();

        termsList.put("Action", "Any event or series of events depicted in a literary work; an event may be verbal as well as physical.");
        termsList.put("Alliteration", "The repition of usually initial consonant sounds through a sequence of words.");
        termsList.put("Allusions", "Brief, often implicit and indirect reference within a literary text or something outside the text, whether another text or imaginary or historical person, place, or thing.");
        termsList.put("Amphitheater", "A theater consisting of a stage area surrounded by a semicircle of tiered seats.");
        termsList.put("Antagonist", "A character or nonhuman force that opposes or is in conflict with the protagonist.");
        termsList.put("Ballad", "A verse narrative that is, or originally was, meant to be sung.");
        termsList.put("Bildungsroman", "Literally, \"education novel\" (German), a novel that depicts the intellectual, emotional, and moral development of its protagonist from childhood into adulthood; also sometimes called apprenticeship novel.");
        termsList.put("Biography", "A work of nonfiction that recounts the life of a real person. If the person depicted in a biography is also its author, then we instead use the term autobiography. An autobiography that focuses only on a specific aspect of, or episode in, its author's life is a memoir.");
        termsList.put("Canon", "The range of works that a consensus of scholars, teachers, and readers of a particular time and culture consider \"great\" or \"major\".");
        termsList.put("Characterization", "The presentation of a fictional personage. A term like \"a good character\" can, then, be ambiguous; it may mean that the personage is virtuous or that he or she is well presented regardless of his or her characteristics or moral qualities.");
        termsList.put("Climax", "The third part of plot, the point at which the action stops rising and begins falling or reversing.");
        termsList.put("Comedy", "A broad category of literary, especially dramatic, works intended primarily to entertain and amuse an audience.");
        termsList.put("Conclusion", "Also called resolution, the fifth and last phase or part of plot, the point at which the situation that was destabalized at the beginning becomes stable once more and the conflict is resolved.");
        termsList.put("Conflict", "Struggle between opposing forces. A conflict is external when it pits a character against something or someone outside himself or herself.");
        termsList.put("Connotation", "What is suggested by a word, apart from what it literally means or how it is defined in the dictionary");
        termsList.put("Convention", "A standard or traditional way of presenting or expressing something, or a traditional or characteristic feature of a particular literary genre or subgenre");
        termsList.put("Denotation", "A words direct and literal meaning, as opposed to its connotation.");
        termsList.put("Dialogue", "Usually, words spoken by characters in a literary work, especially as opposed to words that come directly from the narrator in a work of fiction.");
        termsList.put("Diction", "Choice of words, often described as either informal or colloquial if it resembles everyday speech, or as formal if it is instead lofty, impersonal, and dignified.");
        termsList.put("Drama", "Literary genre consisting of works in which action is performed and all words are spoken before an audience by an actor or actors impersonating the characters.");
        termsList.put("Dramatic Monologue", "A type or subgenre of poetry in which a speaker addresses a silent auditor or auditors in a specific situation and setting that is revealed entirely through the speaker's words.");
        termsList.put("Elegy", "Usually a formal lament on the death of a particular person, but focusing mainly on the speaker's efforts to come to terms with his or her grief.");
        termsList.put("Epic", "Long narrative poem that celebrates the achievements of mighty heroes and heroines, usually in founding a nation or developiong a culture, and uses elevated language and a grand, high style.");
        termsList.put("Exposition", "First phase or part of plot, which sets the scene, introduces and identifies characters, and establishes the situation at the beginning of a story or play.");
        termsList.put("Fables", "An ancient type of short fiction, in verse or prose, illustrating a moral or satirizing human beings. Characters in a fable are often animals that talk and act like human beings. The fable is sometimes treated as a specific type of folktale and sometimes as a fictional subgenre in its own right.");
        termsList.put("Falling Action", "Fourth of the five phases or parts of plot, in which the conflict or conflicts move toward resolution.");
        termsList.put("Farce", "Literary work, especially drama, characterized by broad humor, wild antics, and often slapstick, pratfalls or other physical humor.");
        termsList.put("Fiction", "Any narrative, especially in prose, about invented or imagined characters and action.");
        termsList.put("Figures of Speech", "Any word or phrase that creates a \"figure\" in the mind of the reader by effecting an obvious change in the usual meaning or order of words, by comaring or identifying one thing with another; also called a trope.");
        termsList.put("Flashbacks", "Plot-structuring device whereby a scene from the fictional past is inserted into the fictional present or is dramatized out of order.");
        termsList.put("Foil", "Character that serves as a contrast to another character.");
        termsList.put("Folktales", "An especially common type of tale, the conventions of which include a formulaic beginning and ending; a setting that is not highly particularized in terms of time or place; flat and often stock characters, animal or human; and fairly simple plots");
        termsList.put("Free Verse", "Poetry characterized by varying line lengths, lack of traditional meter, and non rhyming lines.");
        termsList.put("Genres", "The type or category of works sharing particualr formal or textual features and conventions; especially used to refer to the largest categories for classifying literature (fiction, poetry, drama, and nonfiction).");
        termsList.put("Gothic Fiction", "A subgenre of fiction conventionally featuring pilots that involve secrets, mystery, and the supernatural and large, gloomy, and usually antiquated buildings and settings.");
        termsList.put("Hero/Heroine", "Character in a literary work, especially the leading male/female character, who is especially virtuous, usually larger than life, sometimes almost godlike.");
        termsList.put("Historical Fiction", "A subgenre of fiction, of whatever length, in which the temporal setting, or plot time, is significantly earlier than the time in which the work was written (typically a period before the birth of the author).");
        termsList.put("Images", "Broadly defined, any sensory detail or evocation in a work; more narrowly, the use of figurative language to evoke a feeling, to call to mind an idea, or to describe an object.");
        termsList.put("Irony", "Situation or statement characterized by a significant difference between what is expected or understood and what actually happens or is meant.");
        termsList.put("Legend", "Type of tale conventionally set in the real world and in either the present or historical past, based on actual people and events offering an exaggerated or distorted version of the truth about those people and events.");
        termsList.put("Lines (In Poetry)", "In a poem, a discrete organization of words; the length and shape of a line can communicate meaning in a poem, and can be a formal element characterizing a poem, such as the fourteen lines that make up a sonnet.");
        termsList.put("Literary Criticism", "Mainly interpretive (versus evaluative) work written by readers of literary texts, especially professional ones (who are thus known as literary critics). It is \"criticism\" not because it is negative or corrective but because those who write criticism ask probing, analytical, \"critical\" questions about the works they read.");
        termsList.put("Lyric Poetry", "Originaly, a poem meant to be sung to the accompaniment of a lyre; now any relatively short poem in which the speaker expresses his or her thoughts and feelings.");
        termsList.put("", "");

        Set<String> keySet = termsList.keySet();
        List<String> keyList = new ArrayList<>(keySet);

        int size = keyList.size();
        int randomId = new Random().nextInt(size);

        String randomKey = keyList.get(randomId);

        System.out.println(randomKey + ":");

        TypeGUI.setWord(randomKey);
        // TypeGUI.startTimer();

        return termsList.get(randomKey);
    }   // End of getSentence()
}
