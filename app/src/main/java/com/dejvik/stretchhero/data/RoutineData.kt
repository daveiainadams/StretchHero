package com.dejvik.stretchhero.data

import com.dejvik.stretchhero.data.StretchStep
import com.dejvik.stretchhero.data.Routine

object RoutineDataSource {
    private val routines = listOf(
        Routine(
            name = "Beginner Full Body Stretch",
            id = "beginner_full_body_stretch",
            steps = listOf(
                StretchStep("Neck Rolls", 30, "ic_stretch_neck"),
                StretchStep("Shoulder Rolls", 30, "ic_stretch_shoulder"),
                StretchStep("Arm Circles", 30, "ic_stretch_arm_circles"),
                StretchStep("Wrist Stretches", 30, "ic_stretch_wrist"),
                StretchStep("Triceps Stretch", 30, "ic_stretch_triceps"),
                StretchStep("Chest Stretch", 30, "ic_stretch_chest"),
                StretchStep("Upper Back Stretch", 30, "ic_stretch_upper_back"),
                StretchStep("Torso Twist", 30, "ic_stretch_torso_twist"),
                StretchStep("Hip Circles", 30, "ic_stretch_hip_circles"),
                StretchStep("Hamstring Stretch", 30, "ic_stretch_hamstring"),
                StretchStep("Quadriceps Stretch", 30, "ic_stretch_quadriceps"),
                StretchStep("Calf Stretch", 30, "ic_stretch_calf"),
                StretchStep("Ankle Rotations", 30, "ic_stretch_ankle"),
                StretchStep("Child's Pose", 60, "ic_stretch_childs_pose")
            )
        ),
        Routine(
            name = "Morning Wake-up Routine",
            id = "morning_wake_up_routine",
            steps = listOf(
                StretchStep("Overhead Stretch", 20, "ic_stretch_overhead"),
                StretchStep("Cat-Cow Stretch", 30, "ic_stretch_cat_cow"),
                StretchStep("Spinal Twist (lying)", 40, "ic_stretch_spinal_twist_lying"),
                StretchStep("Knee to Chest Stretch", 30, "ic_stretch_knee_to_chest"),
                StretchStep("Glute Bridge", 30, "ic_stretch_glute_bridge")
            )
        ),
        Routine(
            name = "Office Desk Relief",
            id = "office_desk_relief",
            steps = listOf(
                StretchStep("Neck Side Bends", 20, "ic_stretch_neck_side_bends"),
                StretchStep("Shoulder Shrugs", 20, "ic_stretch_shoulder_shrugs"),
                StretchStep("Wrist Flexion and Extension", 30, "ic_stretch_wrist_flex_extend"),
                StretchStep("Seated Torso Twist", 30, "ic_stretch_seated_torso_twist"),
                StretchStep("Upper Trap Stretch", 30, "ic_stretch_upper_trap")
            )
        ),
        Routine(
            name = "Post-Workout Cool Down",
            id = "post_workout_cool_down",
            steps = listOf(
                StretchStep("Lying Hamstring Stretch", 45, "ic_stretch_lying_hamstring"),
                StretchStep("Pigeon Pose", 45, "ic_stretch_pigeon_pose"),
                StretchStep("Triceps Stretch (Overhead)", 30, "ic_stretch_triceps_overhead"),
                StretchStep("Biceps Stretch", 30, "ic_stretch_biceps"),
                StretchStep("Figure-Four Stretch", 40, "ic_stretch_figure_four")
            )
        ),
        Routine(
            name = "Quick Neck & Shoulders",
            id = "quick_neck_shoulders",
            steps = listOf(
                StretchStep("Neck Flexion/Extension", 30, "ic_stretch_neck_flex_extend"),
                StretchStep("Ear to Shoulder", 30, "ic_stretch_ear_to_shoulder"),
                StretchStep("Shoulder Blade Squeezes", 30, "ic_stretch_shoulder_blade_squeeze"),
                StretchStep("Doorway Chest Stretch", 40, "ic_stretch_doorway_chest")
            )
        )
    )

    fun getAllRoutines(): List<Routine> = routines

    fun getRoutineById(id: String): Routine? {
        return routines.find { it.id == id }
    }
}
