package com.booleanull.job.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.booleanull.job.MyApplication
import com.booleanull.job.R
import com.booleanull.job.domain.models.Job
import com.booleanull.job.ui.job.JobFragment
import kotlinx.android.synthetic.main.activity_main.*
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var cicerone: Cicerone<Router>

    private val navigator = object : SupportAppNavigator(this, R.id.nav_host_fragment) {
        override fun applyCommands(commands: Array<Command>) {
            super.applyCommands(commands)
            supportFragmentManager.executePendingTransactions()
        }
    }

    init {
        MyApplication.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            cicerone.router.replaceScreen(Screens.JobScreen())
        }
    }

    fun navigateToDetail(job: Job) {
        cicerone.router.navigateTo(Screens.JobAboutScreen(job))
    }

    override fun onResume() {
        super.onResume()
        cicerone.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        cicerone.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        cicerone.router.exit()
    }
}
