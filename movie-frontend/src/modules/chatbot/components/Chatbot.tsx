import { useState } from "react";
import { sendMessage } from "../service/ChatBotService";

export default function Chatbot() {
    const [isOpen, setIsOpen] = useState(false);
    const [messages, setMessages] = useState([
        { id: 1, role: 'bot', text: 'Xin chào! Bạn muốn tìm phim gì hôm nay?' },
    ]);
    const [input, setInput] = useState("");
    const [isTyping, setIsTyping] = useState(false);

    const handleSend = async () => {
        if (!input.trim()) return;

        const userMsg = { id: Date.now(), role: 'user', text: input };
        setMessages(prev => [...prev, userMsg]);
        const currentInput = input;
        setInput("");
        setIsTyping(true);

        const botMsgId = Date.now() + 1;
        setMessages(prev => [...prev, { id: botMsgId, role: 'bot', text: "" }]);

        try {
            const response = await sendMessage(currentInput)

            if (!response.ok) throw new Error("Network response was not ok");
            if (!response.body) throw new Error("ReadableStream not supported");

            const reader = response.body.getReader();
            const decoder = new TextDecoder();
            let accumulatedText = "";

            while (true) {
                const { value, done } = await reader.read();
                console.log("Reader read - done:", done, "value:", value);

                if (value) {
                    const chunk = decoder.decode(value, { stream: true });
                    console.log("Dữ liệu thô nhận được:", chunk);
                }
                if (done) {
                    console.log("Stream finished");
                    break;
                }

                const chunk = decoder.decode(value, { stream: true });
                const lines = chunk.split("\n");

                for (const line of lines) {
                    let textToParse = line.trim();

                    if (textToParse.startsWith("data:")) {
                        textToParse = textToParse.replace("data:", "").trim();
                    }

                    if (!textToParse) continue;

                    try {
                        const parsed = JSON.parse(textToParse);

                        const content = parsed.message || "";
                        accumulatedText += content;

                        setMessages(prev => prev.map(msg =>
                            msg.id === botMsgId ? { ...msg, text: accumulatedText } : msg
                        ));
                    } catch (e) {
                        console.error("Lỗi parse JSON tại dòng:", textToParse, e);
                    }
                } 
            }
        } catch (error) {
            console.error("Streaming error:", error);
        } finally {
            setIsTyping(false);
        }
    };

    return (
        <div className="fixed bottom-5 right-5 z-[1000] font-sans">
            {!isOpen ? (
                <button
                    onClick={() => setIsOpen(true)}
                    className="bg-nfRed text-white p-4 rounded-full shadow-lg hover:bg-red-700 transition"
                    title="Chatbot"
                >
                    💬
                </button>
            ) : (
                <div className="w-[350px] h-[500px] bg-black border border-nfGrey-400 rounded-2xl shadow-2xl flex flex-col overflow-hidden animate-in fade-in slide-in-from-bottom-4 duration-300">
                    <div className="p-4 bg-black flex justify-between items-center border-b border-nfGrey-400">
                        <div className="flex items-center gap-2">
                            <div className="w-2 h-2 bg-green-500 rounded-full animate-pulse"></div>
                            <span className="text-white font-medium">Netflix AI Assistant</span>
                        </div>
                        <button onClick={() => setIsOpen(false)} className="text-nfGrey-10 hover:text-white">✕</button>
                    </div>

                    <div className="flex-1 overflow-y-auto p-4 space-y-4 custom-scrollbar">
                        {messages.map((msg) => (
                            <div key={msg.id} className={`flex ${msg.role === 'user' ? 'justify-end' : 'justify-start'}`}>
                                <div className={`max-w-[80%] p-3 rounded-2xl text-[14px] ${msg.role === 'user' ? 'bg-nfRed text-white' : 'bg-black text-nfGrey-10'
                                    }`}>
                                    {msg.text}
                                    {isTyping && msg.id === messages[messages.length - 1].id && msg.role === 'bot' && !msg.text && (
                                        <span className="animate-pulse">...</span>
                                    )}
                                </div>
                            </div>
                        ))}
                    </div>

                    <div className="p-3 bg-nfGrey-800 space-y-3">
                        <div className="flex gap-2 overflow-x-auto pb-1 no-scrollbar">
                            {['Phim hành động', 'Top 10 hôm nay', 'Phim mới'].map((txt) => (
                                <button key={txt} className="whitespace-nowrap px-3 py-1 bg-black border border-nfGrey-400 rounded-full text-[11px] text-nfGrey-10 hover:border-white transition">
                                    {txt}
                                </button>
                            ))}
                        </div>
                        <div className="flex gap-2">
                            <input
                                value={input}
                                onChange={(e) => setInput(e.target.value)}
                                onKeyDown={(e) => e.key === 'Enter' && handleSend()}
                                placeholder="Hỏi AI về phim..."
                                className="..."
                            />
                            <button onClick={handleSend} className="text-nfRed font-bold px-2">Gửi</button>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
}