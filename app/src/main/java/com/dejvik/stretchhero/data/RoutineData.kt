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
                StretchStep("Neck Rolls", 30, "ic_stretch_neck", "Gently roll your head in circular motions to release neck tension"),
                StretchStep("Shoulder Rolls", 30, "ic_stretch_shoulder", "Roll your shoulders forward and backward to loosen tight muscles"),
                StretchStep("Arm Circles", 30, "ic_stretch_arm_circles", "Make circular motions with your arms to warm up shoulder joints"),
                StretchStep("Wrist Stretches", 30, "ic_stretch_wrist", "Flex and extend your wrists to improve flexibility"),
                StretchStep("Triceps Stretch", 30, "ic_stretch_triceps", "Stretch your triceps by reaching behind your head"),
                StretchStep("Chest Stretch", 30, "ic_stretch_chest", "Open your chest by stretching your arms back"),
                StretchStep("Upper Back Stretch", 30, "ic_stretch_upper_back", "Stretch your upper back by rounding and arching"),
                StretchStep("Torso Twist", 30, "ic_stretch_torso_twist", "Gently twist your torso to improve spinal mobility"),
                StretchStep("Hip Circles", 30, "ic_stretch_hip_circles", "Make circular motions with your hips to warm up"),
                StretchStep("Hamstring Stretch", 30, "ic_stretch_hamstring", "Stretch your hamstrings by reaching toward your toes"),
                StretchStep("Quadriceps Stretch", 30, "ic_stretch_quadriceps", "Stretch your quads by pulling your foot toward your glutes"),
                StretchStep("Calf Stretch", 30, "ic_stretch_calf", "Stretch your calves by leaning into a wall"),
                StretchStep("Ankle Rotations", 30, "ic_stretch_ankle", "Rotate your ankles to improve ankle mobility"),
                StretchStep("Child's Pose", 60, "ic_stretch_childs_pose", "Relax in child's pose to finish your routine")
            )
        ),
        Routine(
            name = "Morning Wake-up Routine",
            id = "morning_wake_up_routine",
            description = "Start your day right with this energizing morning stretch routine. Perfect for waking up your body and mind.",
            difficulty = Difficulty.BEGINNER,
            steps = listOf(
                StretchStep("Overhead Stretch", 20, "ic_stretch_overhead", "Reach your arms overhead to wake up your body"),
                StretchStep("Cat-Cow Stretch", 30, "ic_stretch_cat_cow", "Flow between cat and cow poses to warm up your spine"),
                StretchStep("Spinal Twist (lying)", 40, "ic_stretch_spinal_twist_lying", "Gently twist your spine while lying down"),
                StretchStep("Knee to Chest Stretch", 30, "ic_stretch_knee_to_chest", "Pull your knees to your chest to stretch your lower back"),
                StretchStep("Glute Bridge", 30, "ic_stretch_glute_bridge", "Lift your hips to activate your glutes and core")
            )
        ),
        Routine(
            name = "Office Desk Relief",
            id = "office_desk_relief",
            description = "Combat the effects of sitting at a desk all day with these targeted stretches. Perfect for office workers.",
            difficulty = Difficulty.BEGINNER,
            steps = listOf(
                StretchStep("Neck Side Bends", 20, "ic_stretch_neck_side_bends", "Gently bend your neck to each side"),
                StretchStep("Shoulder Shrugs", 20, "ic_stretch_shoulder_shrugs", "Shrug your shoulders to release tension"),
                StretchStep("Wrist Flexion and Extension", 30, "ic_stretch_wrist_flex_extend", "Flex and extend your wrists to prevent carpal tunnel"),
                StretchStep("Seated Torso Twist", 30, "ic_stretch_seated_torso_twist", "Twist your torso while seated to improve mobility"),
                StretchStep("Upper Trap Stretch", 30, "ic_stretch_upper_trap", "Stretch your upper trapezius muscles")
            )
        ),
        Routine(
            name = "Post-Workout Cool Down",
            id = "post_workout_cool_down",
            description = "Essential stretches to perform after your workout to improve recovery and prevent muscle tightness.",
            difficulty = Difficulty.INTERMEDIATE,
            steps = listOf(
                StretchStep("Lying Hamstring Stretch", 45, "ic_stretch_lying_hamstring", "Stretch your hamstrings while lying on your back"),
                StretchStep("Pigeon Pose", 45, "ic_stretch_pigeon_pose", "Deep hip opener to release tight hip flexors"),
                StretchStep("Triceps Stretch (Overhead)", 30, "ic_stretch_triceps_overhead", "Stretch your triceps by reaching overhead"),
                StretchStep("Biceps Stretch", 30, "ic_stretch_biceps", "Stretch your biceps by extending your arms"),
                StretchStep("Figure-Four Stretch", 40, "ic_stretch_figure_four", "Cross your legs to stretch your glutes and hips")
            )
        ),
        Routine(
            name = "Quick Neck & Shoulders",
            id = "quick_neck_shoulders",
            description = "A quick routine to relieve neck and shoulder tension. Perfect for breaks during the day.",
            difficulty = Difficulty.BEGINNER,
            steps = listOf(
                StretchStep("Neck Flexion/Extension", 30, "ic_stretch_neck_flex_extend", "Gently nod your head forward and back"),
                StretchStep("Ear to Shoulder", 30, "ic_stretch_ear_to_shoulder", "Tilt your head to bring your ear toward your shoulder"),
                StretchStep("Shoulder Blade Squeezes", 30, "ic_stretch_shoulder_blade_squeeze", "Squeeze your shoulder blades together"),
                StretchStep("Doorway Chest Stretch", 40, "ic_stretch_doorway_chest", "Stretch your chest using a doorway")
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
