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
        ),
        // Physio-Recommended Routines
        Routine(
            name = "Lower Back Pain Relief",
            id = "lower_back_pain_relief",
            description = "Evidence-based stretches to relieve lower back pain and improve spinal mobility. Perfect for desk workers and chronic pain sufferers.",
            difficulty = Difficulty.BEGINNER,
            steps = listOf(
                StretchStep("Knee-to-Chest", 30, "stretch_leg", "Lie on back, pull one knee to chest to decompress spine"),
                StretchStep("Knee-to-Chest (Other Side)", 30, "stretch_leg", "Pull opposite knee to chest, feel lower back release"),
                StretchStep("Cat-Cow Stretch", 40, "stretch_cobra", "On hands and knees, arch and round spine for mobility"),
                StretchStep("Child's Pose", 45, "stretch_childs_pose", "Kneel, sit back on heels, arms forward for gentle stretch"),
                StretchStep("Lumbar Rotation (Right)", 30, "stretch_cobra", "Lie on back, drop knees to right side gently"),
                StretchStep("Lumbar Rotation (Left)", 30, "stretch_cobra", "Drop knees to left side, rotate lumbar spine"),
                StretchStep("Pelvic Tilts", 30, "stretch_leg", "Lie on back, tilt pelvis to flatten lower back"),
                StretchStep("Hamstring Stretch (Right)", 30, "stretch_leg", "Lie on back, straighten right leg up, stretch back of thigh"),
                StretchStep("Hamstring Stretch (Left)", 30, "stretch_leg", "Straighten left leg up, feel hamstring release")
            )
        ),
        Routine(
            name = "Neck & Shoulder Tension Relief",
            id = "neck_shoulder_tension_relief",
            description = "Targeted stretches for office workers and tech neck. Relieve upper body tension and improve posture.",
            difficulty = Difficulty.BEGINNER,
            steps = listOf(
                StretchStep("Chin Tucks", 30, "stretch_neck_rolls", "Tuck chin to chest, strengthen neck alignment"),
                StretchStep("Lateral Neck Flexion (Right)", 30, "stretch_neck_rolls", "Tilt head to right shoulder, stretch left side of neck"),
                StretchStep("Lateral Neck Flexion (Left)", 30, "stretch_neck_rolls", "Tilt head to left shoulder, stretch right side of neck"),
                StretchStep("Upper Trapezius Stretch (Right)", 30, "stretch_neck_rolls", "Tilt head right, gently pull, drop left shoulder"),
                StretchStep("Upper Trapezius Stretch (Left)", 30, "stretch_neck_rolls", "Tilt head left, gently pull, drop right shoulder"),
                StretchStep("Shoulder Rolls", 30, "stretch_shoulder", "Roll shoulders back and down in circles"),
                StretchStep("Doorway Chest Stretch", 40, "stretch_cobra", "Forearm on doorframe, step forward to open chest"),
                StretchStep("Levator Scapulae Stretch (Right)", 30, "stretch_neck_rolls", "Turn head 45° right, nod down toward armpit"),
                StretchStep("Levator Scapulae Stretch (Left)", 30, "stretch_neck_rolls", "Turn head 45° left, nod down toward armpit")
            )
        ),
        Routine(
            name = "Hip Flexibility Quest",
            id = "hip_flexibility_quest",
            description = "Deep hip stretches for tight hips, desk workers, and athletes. Improve mobility and reduce stiffness.",
            difficulty = Difficulty.INTERMEDIATE,
            steps = listOf(
                StretchStep("Kneeling Hip Flexor (Right)", 45, "stretch_leg", "Kneel on left knee, lunge forward to stretch right hip"),
                StretchStep("Kneeling Hip Flexor (Left)", 45, "stretch_leg", "Kneel on right knee, lunge forward to stretch left hip"),
                StretchStep("Pigeon Pose (Right)", 60, "stretch_leg", "Right leg bent in front, left leg extended back, deep hip opener"),
                StretchStep("Pigeon Pose (Left)", 60, "stretch_leg", "Left leg bent in front, right leg extended back, release hip"),
                StretchStep("90/90 Hip Stretch (Right)", 45, "stretch_leg", "Sit with right leg at 90° in front, left at 90° to side"),
                StretchStep("90/90 Hip Stretch (Left)", 45, "stretch_leg", "Switch legs, left in front, improve hip rotation"),
                StretchStep("Butterfly Stretch", 45, "stretch_leg", "Sit, soles together, press knees down gently"),
                StretchStep("Figure-Four Stretch (Right)", 45, "stretch_leg", "Lie on back, cross right ankle over left knee, pull"),
                StretchStep("Figure-Four Stretch (Left)", 45, "stretch_leg", "Cross left ankle over right knee, stretch glute"),
                StretchStep("Supine Hip Flexor (Right)", 30, "stretch_leg", "Lie on back, pull right knee to chest"),
                StretchStep("Supine Hip Flexor (Left)", 30, "stretch_leg", "Pull left knee to chest, release hip flexor")
            )
        ),
        Routine(
            name = "Posture Correction Quest",
            id = "posture_correction_quest",
            description = "Combat forward head posture and rounded shoulders. Essential for desk workers and screen time.",
            difficulty = Difficulty.BEGINNER,
            steps = listOf(
                StretchStep("Chin Tucks", 30, "stretch_neck_rolls", "Strengthen neck, correct forward head posture"),
                StretchStep("Chest Opening Stretch", 40, "stretch_cobra", "Clasp hands behind back, lift chest and squeeze shoulder blades"),
                StretchStep("Doorway Pec Stretch (Right)", 40, "stretch_cobra", "Right arm on doorframe, rotate chest open to left"),
                StretchStep("Doorway Pec Stretch (Left)", 40, "stretch_cobra", "Left arm on doorframe, rotate chest open to right"),
                StretchStep("Shoulder Blade Squeeze", 30, "stretch_shoulder", "Squeeze shoulder blades together, hold for strength"),
                StretchStep("Upper Back Extension", 30, "stretch_cobra", "Hands behind head, lean back gently over chair"),
                StretchStep("Cat-Cow Stretch", 40, "stretch_cobra", "Mobilize spine, improve posture awareness and flexibility")
            )
        ),
        Routine(
            name = "Athletic Recovery Quest",
            id = "athletic_recovery_quest",
            description = "Comprehensive post-workout recovery for athletes and active individuals. Prevent soreness and improve flexibility.",
            difficulty = Difficulty.INTERMEDIATE,
            steps = listOf(
                StretchStep("Standing Quad Stretch (Right)", 40, "stretch_leg", "Pull right foot to glutes, stretch front of thigh"),
                StretchStep("Standing Quad Stretch (Left)", 40, "stretch_leg", "Pull left foot to glutes, balance and stretch"),
                StretchStep("Lying Hamstring Stretch (Right)", 50, "stretch_leg", "Lie on back, right leg up, stretch back of thigh"),
                StretchStep("Lying Hamstring Stretch (Left)", 50, "stretch_leg", "Left leg up, feel hamstring lengthen"),
                StretchStep("Calf Stretch (Right)", 40, "stretch_leg", "Lean into wall, right leg back straight, stretch calf"),
                StretchStep("Calf Stretch (Left)", 40, "stretch_leg", "Left leg back straight, press heel down"),
                StretchStep("Hip Flexor Lunge (Right)", 45, "stretch_leg", "Deep lunge, right leg forward, stretch left hip"),
                StretchStep("Hip Flexor Lunge (Left)", 45, "stretch_leg", "Left leg forward, stretch right hip flexor"),
                StretchStep("Pigeon Pose (Right)", 60, "stretch_leg", "Deep glute and hip stretch, right leg forward"),
                StretchStep("Pigeon Pose (Left)", 60, "stretch_leg", "Left leg forward, release tight glutes"),
                StretchStep("Spinal Twist (Right)", 40, "stretch_cobra", "Lie on back, twist knees to right side"),
                StretchStep("Spinal Twist (Left)", 40, "stretch_cobra", "Twist knees to left side, rotate spine"),
                StretchStep("Child's Pose", 60, "stretch_childs_pose", "Final relaxation, full body release and recovery")
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
