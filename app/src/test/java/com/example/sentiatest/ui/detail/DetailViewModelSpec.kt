package com.example.sentiatest.ui.detail

import android.app.Application
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import com.example.sentiatest.data.Data
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

@ExperimentalCoroutinesApi
class DetailViewModelSpec : Spek({
    val mockApplication = mockk<Application>() {
        every { applicationContext } returns mockk()
    }
    val data = Data(id = "123")
    lateinit var detailViewModel: DetailViewModel

    beforeGroup {
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
        detailViewModel = spyk(DetailViewModel(data, mockApplication))
    }

    afterGroup {
    }

    beforeEachGroup {
    }

    describe("init") {
        it("should return data") {
            runBlockingTest {
                Assertions.assertThat(detailViewModel.data.value).isEqualTo(data)
            }
        }
    }

})
