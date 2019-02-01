package poc.dynamictemplatepoc.onebutton

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import poc.dynamictemplatepoc.model.User
import poc.dynamictemplatepoc.views.firebase.OneButtonContract
import poc.dynamictemplatepoc.views.firebase.OneButtonPresenter

class OneButtonPresenterTest {

    // it is easier to mock interfaces,
    // we can test without wait to actual implementation of
    // model or view
    // unit test is faster then instrumental test
    lateinit var mockModel: OneButtonContract.Model
    lateinit var mockView: OneButtonContract.View
    lateinit var presenter: OneButtonPresenter
    lateinit var user: User

    @Before
    fun runBeforeEachTest() {

        mockModel = mock(OneButtonContract.Model::class.java)

        user = User("paulo", "4399999999")

        `when`(mockModel.getUser()).thenReturn(user)

        mockView = mock(OneButtonContract.View::class.java)

        presenter = OneButtonPresenter(mockModel)

        presenter.setView(mockView)

    }

    @Test
    fun noInteractionWithView() {
        presenter.getCurrentUser()
        verifyZeroInteractions(mockView)
    }
}