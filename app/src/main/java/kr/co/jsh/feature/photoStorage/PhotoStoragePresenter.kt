package kr.co.jsh.feature.photoStorage
//
//import android.annotation.SuppressLint
//import io.reactivex.schedulers.Schedulers
//import kr.co.domain.api.usecase.GetAllImageResultListUseCase
//import timber.log.Timber
//
//class PhotoStoragePresenter(override var view: PhotoStorageContract.View,
//                            private var getAllImageResultListUseCase: GetAllImageResultListUseCase,
//                            private var insertImageDataBaseUseCase: InsertImageDataBaseUseCase,
//                            private var allLoadImageDataBaseUseCase: AllLoadImageDataBaseUseCase,
//                            private var allDeleteImageDataBaseUseCase: AllDeleteImageDataBaseUseCase)
//    :PhotoStorageContract.Presenter{
//
//    private val addRoomDBImageStorage : ArrayList<List<String>> = ArrayList()
//    private val addServerImageStorage : ArrayList<List<String>> = ArrayList()
//    private val resultImageList = ArrayList<String>()
//
//    @SuppressLint("CheckResult")
//    override fun getServerFileResult() {
//        resultImageList.clear()
//        view.startAnimation()
//        allDeleteImageStorage()
//        getAllImageResultListUseCase.getAllImageResult()
//            .subscribe({
//                it.datas.list.map{
//                    it.resultFile?.objectPid?.let{
//                        resultImageList.add("http://192.168.0.188:8080/file/fileDownload.do?objectPid=${it}")
//                        resultImageList.add(it)
//                        addServerImageStorage.add(resultImageList)
//                        insertDataBase(addServerImageStorage)
//                    }
//                }
//                view.setFileResult(addServerImageStorage)
//            },{
//                Timber.e(it.localizedMessage)
//            })
//
//    }
//
//    override fun getLocalFileResult() {
//        view.startAnimation()
//        view.setFileResult(addRoomDBImageStorage)
//    }
//
//
//    //load db
//    @SuppressLint("CheckResult")
//    override fun loadLocalFileStorageDB() {
//        allLoadImageDataBaseUseCase.allLoad()
//            .subscribeOn(Schedulers.io())
//            .subscribe({
//                it.map {
//                    resultImageList.clear()
//                    resultImageList.add(it.path)
//                    resultImageList.add(it.filename)
//                    addRoomDBImageStorage.add(resultImageList) //set 함수
//                }
//
//                //  view.successLoadDB()
//                Timber.e("onComplete")
//
//            }, {
//                Timber.e("Error getting info from interactor (image)")
//            })
//    }
//
//    private fun allDeleteImageStorage(){
//        allDeleteImageDataBaseUseCase.allDelete()
//    }
//    private fun insertDataBase(storage: ArrayList<List<String>>){
//        insertImageDataBaseUseCase.insert(ImageStorage(null, storage[storage.size-1][0], storage[storage.size-1][1]))
//    }
//
//}