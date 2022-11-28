# TheOneApp

This app is designed as a portable wiki for Lord of The Rings / Hobbit fans. The app consumes the "The One API" API, which has many informations about Lord of The Rings and The Hobbit.

TheOneApp has the following functionalities:

 - Show all Lord of The Rings books and it's chapters, with images included;
 - Show all the Lord of The Rings and The Hobbit characters, it's details and quotes;
 - Search one specific character;
 - Show all the Lord of The Rings and The Hobbit movies and it's details.
 
Resources used:
 - Retrofit, for retrieving json data from the API;
 - Koin, for dependency injection;
 - Glide, for image loading;
 - Material Design Components for screen elements;
 - Material Design Colors for color palette;
 
 Screens:
 
![book_list](https://user-images.githubusercontent.com/110692081/204357133-1519e740-d521-46ce-a608-1e0cd084fbdc.png)

This is the first screen of the app, which can be accessed from it's bottom navigation icon, and shows all the Lord of The Rings books with a image cover. You can switch items when swiping the carousel and click on them to see their description.

![book_chapter](https://user-images.githubusercontent.com/110692081/204357444-61d358fe-2fac-40ec-bd92-227a93bc2d6d.png)

After clicking on one book item you are redirected to this description screen, which shows it's name and all the chapters from the selected book.

![character_list](https://user-images.githubusercontent.com/110692081/204357349-bd902bb7-12c3-40a9-aae5-85300a99f10d.png)

Y ou can see this screen after clicking on the character icon in the bottom navigation. It show the name and race of all the Lord of The Rings characters in alphabetical order, and can be sorted with the search bar. Each character item is clickable, and will redirect to a details screen.

![character_description](https://user-images.githubusercontent.com/110692081/204357601-5f8ab9da-b699-4c27-8c64-00edd9fe89c1.png)

After clicking on one character item you are redirected to this description screen, which shows all available details of the character in the API. 

![character_description_2](https://user-images.githubusercontent.com/110692081/204357567-eea7534e-0718-4769-b238-18e88616b5c5.png)

This is the bottom part of the screen, that is shown when you scroll down. It has a quotes button, which redirects to a screen showing all the quotes from the selected character.

![character_quotes](https://user-images.githubusercontent.com/110692081/204357645-6a3c248b-950e-4406-85f5-8189833fc33f.png)

This screen is acessed after clicking on a character's quotes button. It shows all of the character's quotes, including their source.

![movie_list](https://user-images.githubusercontent.com/110692081/204357397-e394429d-341a-42c7-8a91-7b4043d5e832.png)

This screen is accessed from the movie icon in the bottom navigation. It shows a list of all the movies from The Lord Of The Rings and The Hobbit franchise, with it's name at the left and it's Rotten Tomatoes score at the right. Each item is clickable and will redirect you to their description page.

![movie_description](https://user-images.githubusercontent.com/110692081/204357689-40679f2b-adb5-4df6-8261-5dab0254482c.png)

This screen is accessed after clicking on one movie item, and contains information regarding the selected movie.
