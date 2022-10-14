import org.junit.Test

import org.junit.Assert.*

class ChatServiceTest {

    @Test
    fun addFirstMessage() {
        assertTrue(ChatService.addMessage(1, Message("first")))
    }

    @Test
    fun addSecondMessage() {
        ChatService.addMessage(1, Message("first"))
        assertTrue(ChatService.addMessage(1, Message("Second")))
    }

    @Test
    fun getMessagesTrue() {
        ChatService.addMessage(1, Message("first"))
        val chatMessages = ChatService.getMessages(1, 1)
        assertTrue(chatMessages.isNotEmpty())
    }

    @Test(expected = ChatNotFoundException::class)
    fun getMessages() {
        ChatService.addMessage(1, Message("first"))
        val chatMessages = ChatService.getMessages(2, 1)
        assertTrue(chatMessages.isNotEmpty())
    }

    @Test
    fun countUnreadOne() {
        ChatService.addMessage(1, Message("first"))
        assertTrue(ChatService.countUnread() == 1 )
    }

    @Test
    fun countUnreadTwo() {
        ChatService.addMessage(1, Message("first"))
        ChatService.addMessage(1, Message("Second"))
        assertTrue(ChatService.countUnread() == 1 )
    }

    @Test
    fun countUnreadThree() {
        ChatService.addMessage(1, Message("first"))
        ChatService.addMessage(2, Message("first"))
        assertTrue(ChatService.countUnread() == 2 )
    }

    @Test
    fun deleteChatTrue() {
        ChatService.addMessage(1, Message("first"))
        ChatService.addMessage(2, Message("first"))
        assertTrue(ChatService.deleteChat(2))
    }

    @Test (expected = ChatNotFoundException::class)
    fun deleteChat() {
        ChatService.addMessage(1, Message("first"))
        assertTrue(ChatService.deleteChat(2))
    }
}

