package com.example.balloon_demo

import android.view.View
import android.widget.Button
import androidx.navigation.fragment.NavHostFragment
import com.skydoves.balloon.ArrowOrientation
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.BalloonHighlightAnimation
import com.skydoves.balloon.BalloonSizeSpec

class TutorialManager(private val activity: MainActivity) {

    private var currentBalloon: Balloon? = null

    fun startTutorial() {
        val navHostFragment = activity.supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        val firstFragment = navHostFragment.childFragmentManager
            .fragments?.firstOrNull() as FirstFragment

        firstFragment.view?.post {
            val nextButton = firstFragment.view?.findViewById<Button>(R.id.button_first)
            nextButton?.let { showFirstBalloon(it) }
        }
    }

    private fun showFirstBalloon(nextButton: View) {
        currentBalloon = Balloon.Builder(activity)
            .setText("Press here to navigate to the second fragment")
            .setTextColorResource(android.R.color.white)
            .setTextSize(16f)
            .setPadding(12)
            .setArrowSize(10)
            .setArrowOrientation(ArrowOrientation.BOTTOM)
            .setArrowPosition(0.5f)
            .setCornerRadius(8f)
            .setBackgroundColorResource(android.R.color.holo_blue_dark)
            .setBalloonAnimation(BalloonAnimation.FADE)
            .setBalloonHighlightAnimation(BalloonHighlightAnimation.SHAKE)
            .setLifecycleOwner(activity)
            .setDismissWhenClicked(true)
            .setDismissWhenTouchOutside(true)
            .setOnBalloonDismissListener {
                showSecondBalloon()
            }
            .build()

        currentBalloon?.showAlignTop(nextButton)
    }

    private fun showSecondBalloon() {
        currentBalloon = Balloon.Builder(activity)
            .setText("Press here to send a message")
            .setTextColorResource(android.R.color.white)
            .setTextSize(16f)
            .setPadding(12)
            .setArrowSize(10)
            .setArrowOrientation(ArrowOrientation.BOTTOM)
            .setArrowPosition(0.82f)
            .setCornerRadius(8f)
            .setBackgroundColorResource(android.R.color.holo_blue_dark)
            .setBalloonAnimation(BalloonAnimation.FADE)
            .setBalloonHighlightAnimation(BalloonHighlightAnimation.SHAKE)
            .setLifecycleOwner(activity)
            .setDismissWhenClicked(true)
            .setDismissWhenTouchOutside(true)
            .build()

        currentBalloon?.showAlignTop(activity.binding.fab)
    }

    fun dismissAll() {
        currentBalloon?.dismiss()
        currentBalloon = null
    }
}
