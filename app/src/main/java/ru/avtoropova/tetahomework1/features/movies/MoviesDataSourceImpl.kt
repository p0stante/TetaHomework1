package ru.avtoropova.tetahomework1.features.movies

import ru.avtoropova.tetahomework1.data.dto.ActorDto
import ru.avtoropova.tetahomework1.data.dto.MovieDto

class MoviesDataSourceImpl : MoviesDataSource {
    override fun getMovies() = listOf(
        MovieDto(
            title = "Гнев человеческий",
            description = "Эйч — загадочный и холодный на вид джентльмен, но внутри него пылает жажда справедливости. Преследуя...",
            rateScore = 3,
            ageRestriction = 18,
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5JP9X5tCZ6qz7DYMabLmrQirlWh.jpg",
            actors = arrayListOf(
                ActorDto(
                    "Джейсон Стэйтем",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lldeQ91GwIVff43JBrpdbAAeYWj.jpg"
                ),
                ActorDto(
                    "Холт Маккэллани",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/8NvOcP35qv5UHWEdpqAvQrKnQQz.jpg"
                ),
                ActorDto(
                    "Джош Хартнетт",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/dCfu2EN7FjISACcjilaJu7evwEc.jpg"
                )
            )
        ),
        MovieDto(
            title = "Мортал Комбат",
            description = "Боец смешанных единоборств Коул Янг не раз соглашался проиграть за деньги. Он не знает о своем наследии...",
            rateScore = 5,
            ageRestriction = 18,
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pMIixvHwsD5RZxbvgsDSNkpKy0R.jpg",
            actors = arrayListOf(
                ActorDto(
                    "Льюис Тан",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lkW8gh20BuwzHecXqYH1eRVuWpb.jpg"
                ),
                ActorDto(
                    "Джессика Макнэйми",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/aAfaMEEqD8syHv5bLi5B3sccrM2.jpg"
                ),
                ActorDto(
                    "Джош Лоусон",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/Am9vM77uZd9bGODugwmWtOfzx6E.jpg"
                )
            )
        ),
        MovieDto(
            title = "Упс... Приплыли!",
            description = "От Великого потопа зверей спас ковчег. Но спустя полгода скитаний они готовы сбежать с него куда угодно...",
            rateScore = 5,
            ageRestriction = 6,
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/546RNYy9Wi5wgboQ7EtD6i0DY5D.jpg",
            actors = arrayListOf(
                ActorDto(
                    "Тара Флинн",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/17gBs4aux2NcnMvf3DK5UKUFttn.jpg"
                ),
                ActorDto(
                    "Ава Коноли",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/o8uE77C4wQHYHJW6En192kjxJGd.jpg"
                ),
            )
        ),
        MovieDto(
            title = "The Box",
            description = "Уличный музыкант знакомится с музыкальным продюсером, и они вдвоём отправляются в путешествие...",
            rateScore = 4,
            ageRestriction = 12,
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/fq3DSw74fAodrbLiSv0BW1Ya4Ae.jpg",
            actors = arrayListOf(
                ActorDto(
                    "Кэмерон Диаз",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/d4f4cQ9EiYuvNMjT1IB2h06KoRx.jpg"
                ),
                ActorDto(
                    "Джеймс Марсден",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/mk142GG0saiSXALY6V4wWcmPROW.jpg"
                ),
            )
        ),
        MovieDto(
            title = "Сага о Дэнни Эрнандесе",
            description = "Tekashi69 или Сикснайн — знаменитый бруклинский рэпер с радужными волосами — прогремел...",
            rateScore = 2,
            ageRestriction = 18,
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5xXGQLVtTAExHY92DHD9ewGmKxf.jpg",
            actors = arrayListOf(
                ActorDto(
                    "6ix9ine",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xAlvyeC9zLbygGMxmmyTHymwuZP.jpg"
                ),
            )
        ),
        MovieDto(
            title = "Пчелка Майя",
            description = "Когда упрямая пчелка Майя и ее лучший друг Вилли спасают принцессу-муравьишку, начинается сказочное...",
            rateScore = 4,
            ageRestriction = 0,
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xltjMeLlxywym14NEizl0metO10.jpg",
            actors = arrayListOf(
                ActorDto(
                    "Бенсон Джек Энтони",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/aVfEldX1ksEMrx45yNBAf9MAIDZ.jpg"
                ),
            )
        ),
        MovieDto(
            title = "Круэлла",
            description = "Невероятно одаренная мошенница по имени Эстелла решает сделать себе имя в мире моды.",
            rateScore = 4,
            ageRestriction = 12,
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hUfyYGP9Xf6cHF9y44JXJV3NxZM.jpg",
            actors = arrayListOf(
                ActorDto(
                    "Эмма Стоун",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/2hwXbPW2ffnXUe1Um0WXHG0cTwb.jpg"
                ),
                ActorDto(
                    "Эмма Томпсон",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xr8Ki3CIqweWWqS5q0kUYdiK6oQ.jpg"
                ),
            )
        ),
        MovieDto(
            title = "Чёрная вдова",
            description = "Чёрной Вдове придется вспомнить о том, что было в её жизни задолго до присоединения к команде Мстителей",
            rateScore = 3,
            ageRestriction = 16,
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/mbtN6V6y5kdawvAkzqN4ohi576a.jpg",
            actors = arrayListOf(
                ActorDto(
                    "Скарлет Йохансон",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6NsMbJXRlDZuDzatN2akFdGuTvx.jpg"
                ),
            )
        )
    )
}