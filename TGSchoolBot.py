import telebot
from telebot import types

bot = telebot.TeleBot('7775815206:AAFJZaUnGL_p8RteDu9Mv3dVRz7u1Z-gzCw')

@bot.message_handler(commands=['start'])
def start(message):
    markup = types.ReplyKeyboardMarkup(resize_keyboard=True)
    web_app_button = types.KeyboardButton('Open', web_app=types.WebAppInfo(url='https://dertgd.github.io/www/'))
    markup.add(web_app_button)

    bot.send_photo(message.chat.id, photo='https://cdn-edge.kwork.ru/pics/t3/68/33020199-662f3b28999a9.jpg',
                   caption="Спасибо, что решили воспользоваться нашим приложением! Мы надеемся, что вам понравится.\n"
                           "Над проектом работали:\n"
                           "@Krugersad\n"
                           "@ngcvfb\n"
                           "@hummerbalenciaga\n"
                           "@sc0pexww\n"
                           "Вы можете открыть сайт, используя кнопку ниже.", reply_markup=markup)

if __name__ == "__main__":
    try:
        bot.polling(none_stop=True)
    except Exception as e:
        print(f"An error occurred: {e}")
