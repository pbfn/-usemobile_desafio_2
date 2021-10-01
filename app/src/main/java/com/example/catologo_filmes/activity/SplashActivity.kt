package com.example.catologo_filmes.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.catologo_filmes.R
import com.example.catologo_filmes.viewModel.SplashViewModel

class SplashActivity : AppCompatActivity() {


    private lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupViewModel()
        verifyRequest()
        observeData()
    }

    private fun setupViewModel(){
        splashViewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
    }

    private fun verifyRequest(){
        if (splashViewModel.getMovies(this)>0){
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }else{
            splashViewModel.getIdMovies(this)
        }
    }

    private fun observeData(){
        splashViewModel.filmesId.observe(this,{listIds ->
         //trava para nao executar muitas requisições, visto que a api não permite mais de 100 por dia.
          var aux:Int = 0
            for (movie in listIds.items){
                if(aux<=40){
                    splashViewModel.getDetailsFilms(movie.id,applicationContext)
                    aux += 1
                }
            }
        })

        val intent= Intent(this,MainActivity::class.java)

        splashViewModel.callBodyDetails.observe(this,{
            startActivity(intent)
        })
    }
}
