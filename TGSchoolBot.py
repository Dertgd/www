
import telebot
from telebot import types

bot = telebot.TeleBot('7775815206:AAFJZaUnGL_p8RteDu9Mv3dVRz7u1Z-gzCw')


@bot.message_handler(commands=['start'])
def start(message):
    user_id = message.from_user.id
    username = message.from_user.username if message.from_user.username else "no_username"

    markup = types.InlineKeyboardMarkup()
    web_app_button = types.InlineKeyboardButton(
        'Open',
        web_app=types.WebAppInfo(url=f'https://dertgd.github.io/www/?user_id={user_id}&username={username}')
    )
    markup.add(web_app_button)

    bot.send_photo(
        message.chat.id,
        photo='https://cdn-edge.kwork.ru/pics/t3/68/33020199-662f3b28999a9.jpg',
        caption=("Спасибо, что решили воспользоваться нашим приложением! Мы надеемся, что вам понравится.\n"
                 "Над проектом работали:\n"
                 "@Krugersad\n"
                 "@ngcvfb\n"
                 "@hummerbalenciaga\n"
                 "@sc0pexww\n"
                 "Вы можете открыть сайт, используя кнопку ниже."),
        reply_markup=markup
    )


if __name__ == "__main__":
    try:
        print("StartingBot")
        bot.polling(none_stop=True)
    except Exception as e:
        print(f"An error occurred: {e}")
