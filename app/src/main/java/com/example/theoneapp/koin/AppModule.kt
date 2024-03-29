package com.example.theoneapp.koin

import com.example.theoneapp.repository.Repository
import com.example.theoneapp.repository.RepositoryImpl
import com.example.theoneapp.retrofit.ClientRetrofit
import com.example.theoneapp.ui.books.bookList.BooksViewModel
import com.example.theoneapp.ui.books.bookDescription.BookDescriptionViewModel
import com.example.theoneapp.ui.characters.characterQuotes.CharacterQuotesViewModel
import com.example.theoneapp.ui.characters.characterList.CharactersViewModel
import com.example.theoneapp.ui.movies.movieList.MoviesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module(override = true) {
    viewModel {
        BooksViewModel(repository = get())
    }
    viewModel {
        MoviesViewModel(repository = get())
    }
    viewModel{
        CharactersViewModel(repository = get())
    }
    viewModel{
        CharacterQuotesViewModel(repository = get())
    }
    viewModel{
        BookDescriptionViewModel(repository = get())
    }
}

val repositoryModule = module(override = true) {
    single<Repository> {
        RepositoryImpl(api = get())
    }
}


val dataModule = module {
    single {
        ClientRetrofit.create(androidContext())
    }
}



