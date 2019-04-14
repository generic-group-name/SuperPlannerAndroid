package com.genericgroupname.supperplannerandroid.Exercise;

import com.genericgroupname.supperplannerandroid.User.User;

import java.util.ArrayList;
import java.util.Random;

public class ExerciseManager {
    private ArrayList<Exercise> exercises;
    private Random rand = new Random();
    public ExerciseManager() {
        this.exercises = new ArrayList<>();
        exercises.add(new Exercise(ExerciseType.EYE,"Palming","Rub the palms of your hands vigorously, until they are warm and place them gently over your eyelids. Let the warmth of the palms transfer onto the eyes. You can feel the eye muscles relax as your eyes find relief in the darkness. Persist until the heat from the hands has been completely absorbed by the eyes. Repeat two to three times.","https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
        exercises.add(new Exercise(ExerciseType.EYE,"Blinking","Sit comfortably with your eyes open. Blink 10-15 times very quickly. Close your eyes and relax for 20 seconds. Repeat this 4-5 times." ,"https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
        exercises.add(new Exercise(ExerciseType.EYE,"Zooming","As you may guessed from the name, you make an object zoom for your eyes so as to shift the focus of your vision. Sit on a chair with your arm outstretched and your thumb up. Now, bend your arm gradually and draw it closer to your eyes, thus zooming the thumb in focus. ","https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
        exercises.add(new Exercise(ExerciseType.EYE,"Shifting","Shifting is about moving or rotating your eyeballs from one direction to another. Look to your rightward corner and then shift your gaze gradually to the opposite direction. The tiny eye muscles get more active and healthy with the spurt of blood pumped in from the shifting. ","https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
        exercises.add(new Exercise(ExerciseType.EYE,"Figure of eight","Sit with legs straight in front of your body with the left hand on the left knee. Hold the right fist above the right knee, with a straight elbow and the thumb pointing upwards. Keep your head still and focus your eyes on the thumb. Draw a figure eight with your thumb, keeping the elbow straight all the time.","https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
        exercises.add(new Exercise(ExerciseType.BACK,"Chest Stretch","In a seated or standing position, take the arms behind you and, if you can, lace your fingers together. Straighten the arms and gently lift your hands up a few inches until you feel a stretch in your chest.  Hold for 10-30 seconds.",""));
        exercises.add(new Exercise(ExerciseType.BACK,"Shoulder Shrugs","Seated or standing, lift the shoulders up towards the ears, squeezing them as hard as you can. Hold for 1-2 seconds and roll them back as you relax down. Repeat for 8-10 reps and then roll the shoulders forward.",""));
        exercises.add(new Exercise(ExerciseType.BACK,"Upper Back Stretch","Seated or standing, stretch the arms straight out and rotate the hands so that the palms face away from each other. Cross the arms so that the palms are pressed together, contract the abs and round the back, reaching away as you relax the head.",""));
        exercises.add(new Exercise(ExerciseType.BACK,"Spinal Twist", "In a seated position with the feet flat on the floor, contract the abs and gently rotate the torso towards the right, using your hands on the chair handles to help deepen the stretch.Only twist as far as you comfortably can and keep the back straight while keeping the hips square. Hold for 10-30 seconds and repeat on the other side.\n",""));
        exercises.add(new Exercise(ExerciseType.BACK,"Forearm Stretch", "Seated or standing, stretch the right arm out and turn the hand down so that the fingers point towards the floor. Use the left hand to gently pull the fingers towards you, feeling a stretch in the forearm. Hold for 10-30 seconds and repeat on the other hand.",""));
        exercises.add(new Exercise(ExerciseType.MIND,"Think grateful thoughts","n a lot of ways, gratefulness is the exact opposite of bitterness. When you carry bitter thoughts in your mind, you will be stressed and easily angered at all times. Bitterness is rust that eats the peace in us gradually until we develop demons within ourselves. Gratefulness, on the other hand, is the first big step to peace of mind. When you are grateful, you are happy with the things that you have, instead of regretting the things that you don’t. It will make your mind feel lighter and more peaceful.",""));
        exercises.add(new Exercise(ExerciseType.MIND,"Meditation","Meditation is the act of training the mind to reach a level of consciousness that the mind can attain. In many cultures, meditation is used to clear the mind and achieve a sense of peace. Although there are certain practices involved in meditation that can be learned through years of practice, you can meditate on your own. Start by going somewhere or space where you feel peaceful. Clear your mind and think of peaceful thoughts. From a few minutes to a few hours, meditation can boost your ability to control your peace of mind.",""));
        exercises.add(new Exercise(ExerciseType.MIND,"Use your senses","Sometimes, we get too busy with our careers and daily lives that we take for granted the simple things that we have or the little things that we can do, such as breathe and smell the flowers. It may sound cliché but going back to the basics is actually a good way to reset your mind and attain peace. Appreciate how your eyes get to see the beauty of nature every day. From the natural gardens to the concrete jungle, everything is interconnected and you are part of it. Taste new dishes and explore the world by exposing your senses to appreciate all the world has to offer. This practice, as simple as it may be, can make you connect with yourself and bring you inner peace.",""));
        exercises.add(new Exercise(ExerciseType.MIND,"Go for a walk","Boosting your inner peace can sometimes be as simple as going out for a walk. When the mind becomes too busy and full, you need alone time to refresh and replenish your energy. Thinking can drain out your energy too. Going for a walk and inhaling fresh air can help your mind and body relax. This is a simple therapy that can loosen up the tightened muscles in your body due to stress and tension. Going for a walk will allow you to clear your mind or organise your thoughts.",""));
        exercises.add(new Exercise(ExerciseType.MIND,"Define your higher values","It is always a choice that you alone can make. You can help yourself by defining your higher values. When stressed out, it is easy to succumb to tiredness, depression and even bitterness. However, if you live by your higher values and think about them every day, then you can look at challenges in the eye and get through them easily. Knowing this can give you a strong peace of mind.",""));
    }
    public Exercise getRandomExercise(User user){
         ArrayList<Exercise> doubledExercises= new ArrayList<>();

            for (Exercise exercise : exercises) {
                if(user.getPreferredEye()&&exercise.getExerciseType()==ExerciseType.EYE)
                    doubledExercises.add(exercise);
                if(user.getPreferredBack()&&exercise.getExerciseType()==ExerciseType.BACK)
                    doubledExercises.add(exercise);
                if(user.getPreferredPsycho()&& exercise.getExerciseType() == ExerciseType.MIND)
                    doubledExercises.add(exercise);

            }
            exercises.addAll(doubledExercises);
        int n = rand.nextInt(exercises.size());
        return exercises.get(n);
    }
}
