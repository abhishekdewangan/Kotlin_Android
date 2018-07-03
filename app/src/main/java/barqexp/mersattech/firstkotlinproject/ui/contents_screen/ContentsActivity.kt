package barqexp.mersattech.firstkotlinproject.ui.contents_screen

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import barqexp.mersattech.firstkotlinproject.R
import barqexp.mersattech.firstkotlinproject.utils.Keys
import kotlinx.android.synthetic.main.layout_content_list.*

class ContentsActivity : AppCompatActivity() {
    private lateinit var contentType: String
    private lateinit var contentFilterType: String
    private lateinit var activityTitle: String
    private lateinit var adapter: ContentsAdapter
    private lateinit var viewModel: ContentsViewModel
    private var isRefresh: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_content_list)
        contentType = intent.extras.getString(Keys.BUNDLE_CONTENT_TYPE)
        contentFilterType = intent.extras.getString(Keys.BUNDLE_CONTENT_FILTER_TYPE)
        activityTitle = intent.extras.getString(Keys.BUNDLE_TITLE)
        initialSetup()
        setAdapter()
        initViewModel()
        subscribeToContents()
        subscribeToLoadingState()
    }

    private fun initialSetup() {

        toolbar.run {
            setTitle(activityTitle)
            setTitleTextColor(ContextCompat.getColor(context, R.color.white))
            setNavigationIcon(R.drawable.ic_back)
            setNavigationOnClickListener({ onBackPressed() })
        }

        refreshLayout.setOnRefreshListener { }

    }

    private fun initViewModel() {
        val viewModelFactory = ContentsViewModel.Factory(application, contentType, contentFilterType)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ContentsViewModel::class.java)
    }

    private fun setAdapter() {
        adapter = ContentsAdapter()
        recyclerContents.layoutManager = LinearLayoutManager(this)
        recyclerContents.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerContents.adapter = adapter
    }

    private fun subscribeToContents() {
        viewModel.getContents(isRefresh).observe(this, Observer {
            if (null != it) {
                adapter.setData(it.toList())
            }
        })
    }

    private fun subscribeToLoadingState() {
        viewModel.isLoadingLiveData.observe(this, Observer {
            if (null != it) {
                when (it) {
                    Keys.LOADING_FRESH -> refreshLayout.isRefreshing = true
                    Keys.LOADING_NEXT -> adapter.addLoader(true)
                    Keys.STOP_LOADING -> refreshLayout.isRefreshing = false
                    Keys.STOP_PAGINATION -> adapter.addLoader(false)
                }
            }
        })
    }
}