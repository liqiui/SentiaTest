package com.example.sentiatest.ui.home

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import com.example.sentiatest.data.Data
import io.mockk.spyk
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

@ExperimentalCoroutinesApi
class HomeViewModelSpec : Spek({
    val mainThreadSurrogate = newSingleThreadContext("UI thread")
    lateinit var homeViewModel: HomeViewModel

    beforeGroup {
        Dispatchers.setMain(mainThreadSurrogate)
        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) {
                runnable.run()
            }

            override fun isMainThread(): Boolean {
                return true
            }

            override fun postToMainThread(runnable: Runnable) {
                runnable.run()
            }
        })
        homeViewModel = spyk(HomeViewModel())
    }

    afterGroup {
        ArchTaskExecutor.getInstance().setDelegate(null)
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    beforeEachGroup {
    }

    describe("init") {
        it("should return null") {
            runBlockingTest {
                assertThat(homeViewModel.result.value).isNull()
            }
        }
    }

    describe("#${HomeViewModel::displayDataDetails.name}") {
        it("should return valid value") {
            runBlockingTest {
                val data = Data(id = "This is function test")
                homeViewModel.displayDataDetails(data)
                assertThat(homeViewModel.selectedData.value).isEqualTo(data)
            }
        }
    }

    describe("#${HomeViewModel::displayDataDetailComplete.name}") {
        it("should return null") {
            homeViewModel.displayDataDetailComplete()
            assertThat(homeViewModel.selectedData.value).isNull()
        }
    }
})
