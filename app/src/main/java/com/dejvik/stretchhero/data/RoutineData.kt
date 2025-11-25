package com.dejvik.stretchhero.data

import com.dejvik.stretchhero.data.StretchStep
import com.dejvik.stretchhero.data.Routine
import com.dejvik.stretchhero.data.Difficulty

object RoutineDataSource {
    private val routines = listOf(
        Routine(
            name = "Beginner Full Body Stretch",
            id = "beginner_full_body_stretch",
            description = "A comprehensive full-body stretching routine perfect for beginners. This routine targets all major muscle groups with gentle, effective stretches.",
            difficulty = Difficulty.BEGINNER,
            steps = listOf(
                StretchStep("Neck Rolls", 30, "stretch_neck_rolls", "Gently roll your head in circular motions to release neck tension"),
                StretchStep("Shoulder Rolls", 30, "stretch_shoulder", "Roll your shoulders forward and backward to loosen tight muscles"),
                StretchStep("Arm Circles", 30, "stretch_shoulder", "Make circular motions with your arms to warm up shoulder joints"),
                StretchStep("Wrist Stretches", 30, "stretch_shoulder", "Flex and extend your wrists to improve flexibility"),
                StretchStep("Triceps Stretch", 30, "stretch_shoulder", "Stretch your triceps by reaching behind your head"),
                StretchStep("Chest Stretch", 30, "stretch_cobra", "Open your chest by stretching your arms back"),
                StretchStep("Upper Back Stretch", 30, "stretch_cobra", "Stretch your upper back by rounding and arching"),
                StretchStep("Torso Twist", 30, "stretch_cobra", "Gently twist your torso to improve spinal mobility"),
                StretchStep("Hip Circles", 30, "stretch_leg", "Make circular motions with your hips to warm up"),
                StretchStep("Hamstring Stretch", 30, "stretch_leg", "Stretch your hamstrings by reaching toward your toes"),
                StretchStep("Quadriceps Stretch", 30, "stretch_leg", "Stretch your quads by pulling your foot toward your glutes"),
                StretchStep("Calf Stretch", 30, "stretch_leg", "Stretch your calves by leaning into a wall"),
                StretchStep("Ankle Rotations", 30, "stretch_leg", "Rotate your ankles to improve ankle mobility"),
                StretchStep("Child's Pose", 60, "stretch_childs_pose", "Relax in child's pose to finish your routine")
            )
        ),
        Routine(
            name = "Morning Wake-up Routine",
            id = "morning_wake_up_routine",
            description = "Start your day right with this energizing morning stretch routine. Perfect for waking up your body and mind.",
            difficulty = Difficulty.BEGINNER,
            steps = listOf(
                StretchStep("Overhead Stretch", 20, "stretch_shoulder", "Reach your arms overhead to wake up your body"),
                StretchStep("Cat-Cow Stretch", 30, "stretch_cobra", "Flow between cat and cow poses to warm up your spine"),
                StretchStep("Spinal Twist (lying)", 40, "stretch_cobra", "Gently twist your spine while lying down"),
                StretchStep("Knee to Chest Stretch", 30, "stretch_leg", "Pull your knees to your chest to stretch your lower back"),
                StretchStep("Glute Bridge", 30, "stretch_leg", "Lift your hips to activate your glutes and core")
            )
        ),
        Routine(
            name = "Office Desk Relief",
            id = "office_desk_relief",
            description = "Combat the effects of sitting at a desk all day with these targeted stretches. Perfect for office workers.",
            difficulty = Difficulty.BEGINNER,
            steps = listOf(
                StretchStep("Neck Side Bends", 20, "stretch_neck_rolls", "Gently bend your neck to each side"),
                StretchStep("Shoulder Shrugs", 20, "stretch_shoulder", "Shrug your shoulders to release tension"),
                StretchStep("Wrist Flexion and Extension", 30, "stretch_shoulder", "Flex and extend your wrists to prevent carpal tunnel"),
                StretchStep("Seated Torso Twist", 30, "stretch_cobra", "Twist your torso while seated to improve mobility"),
                StretchStep("Upper Trap Stretch", 30, "stretch_shoulder", "Stretch your upper trapezius muscles")
            )
        ),
        Routine(
            name = "Post-Workout Cool Down",
            id = "post_workout_cool_down",
            description = "Essential stretches to perform after your workout to improve recovery and prevent muscle tightness.",
            difficulty = Difficulty.INTERMEDIATE,
            steps = listOf(
                StretchStep("Lying Hamstring Stretch", 45, "stretch_leg", "Stretch your hamstrings while lying on your back"),
                StretchStep("Pigeon Pose", 45, "stretch_leg", "Deep hip opener to release tight hip flexors"),
                StretchStep("Triceps Stretch (Overhead)", 30, "stretch_shoulder", "Stretch your triceps by reaching overhead"),
                StretchStep("Biceps Stretch", 30, "stretch_shoulder", "Stretch your biceps by extending your arms"),
                StretchStep("Figure-Four Stretch", 40, "stretch_leg", "Cross your legs to stretch your glutes and hips")
            )
        ),
        Routine(
            name = "Quick Neck & Shoulders",
            id = "quick_neck_shoulders",
            description = "A quick routine to relieve neck and shoulder tension. Perfect for breaks during the day.",
            difficulty = Difficulty.BEGINNER,
            steps = listOf(
                StretchStep("Neck Flexion/Extension", 30, "stretch_neck_rolls", "Gently nod your head forward and back"),
                StretchStep("Ear to Shoulder", 30, "stretch_neck_rolls", "Tilt your head to bring your ear toward your shoulder"),
                StretchStep("Shoulder Blade Squeezes", 30, "stretch_shoulder", "Squeeze your shoulder blades together"),
                StretchStep("Doorway Chest Stretch", 40, "stretch_cobra", "Stretch your chest using a doorway")
            )
        )
    )

    fun getAllRoutines(): List<Routine> = routines

    fun getRoutineById(id: String): Routine? {
        return routines.find { it.id == id }
    }
    
    fun getRoutinesByDifficulty(difficulty: Difficulty): List<Routine> {
        return routines.filter { it.difficulty == difficulty }
    }
    
    fun getRoutinesByDuration(maxDurationMinutes: Int): List<Routine> {
        return routines.filter { it.totalDurationInMinutes <= maxDurationMinutes }
    }
}
