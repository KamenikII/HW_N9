import ChatNotFoundException

data class Chat(val messages: MutableList<Message> = mutableListOf())
data class Message(val content: String, var read: Boolean = false)

object ChatService {
    private val chats = mutableMapOf<Int, Chat>() //<Id, chat>

    fun addMessage(userId: Int, message: Message): Boolean {
        if (chats[userId] != null) chats[userId]?.messages?.add(message)
        else {
            val chat = Chat().also {it.messages.add(message)}
            chats[userId] = chat
        }
        return true
    }

    fun getMessages(userId: Int, startFrom: Int): List<Message> {
//        val chat = chats[userId] ?: throw ChatNotFoundException()
//        return chat.messages.takeLast(n).onEach {it.read = true}
        chats.singleOrNull { it.id == chatId}
            .let { it?.messages ?: throw ChatNotFoundException() }
            .asSequence()
            .drop(startFrom)
            .ifEmpty { throw MessageNotFoundException() }
            .toList()
    }

    fun countUnread(): Int {
        return chats.values.count {chat -> chat.messages.any{!it.read }}
    }

    fun deleteChat(userId: Int): Boolean {
        if (chats[userId] != null) {
            chats.remove(userId)
            return true
        } else throw ChatNotFoundException()
    }
}

class ChatNotFoundException: Throwable() {}
class MessageNotFoundException: Throwable() {}
