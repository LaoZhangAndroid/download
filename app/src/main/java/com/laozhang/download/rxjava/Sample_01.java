package com.laozhang.download.rxjava;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class Sample_01 {

    public static void main(String[] args){
        Sample_01 sample_01 = new Sample_01();
//        new Sample_01().test();
//        sample_01.test01();
//        sample_01.test02();
//        sample_01.test03();
//        sample_01.test04();
//        sample_01.test05();
        sample_01.test06();
    }


    private void println(String text){
        System.out.println(text);
    }

    //create
    private void test() {
        //使用create()创建一个被观察者对象
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
//                emitter.onError(new NullPointerException());
                emitter.onComplete();
            }
        });
        //观察者订阅事件
        observable.subscribe(new Observer<Integer>() {
            private Disposable disposable;//用于取消订阅

            @Override
            public void onSubscribe(Disposable d) {
                println("onSubscribe");
                disposable = d;
            }

            @Override
            public void onNext(Integer integer) {
                println("onNext" + integer);
                if(integer==2) disposable.dispose();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                println("onComplete");
            }
        });
    }

    //线程调度
    private void test01(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(0);
                emitter.onComplete();
                println("发射线程为"+Thread.currentThread().getName());
            }
        }).subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        println("观察线程为"+Thread.currentThread().getName());
                    }
                });
    }

    //map
    private void test02(){
        //map()对发送的数据进行转换
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("hello motor");
                emitter.onComplete();
            }
        }).map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Exception {
                return s==null? 0 : s.length();
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                println("字符串长度为"+integer);
            }
        });

    }

    //concat
    private void test03(){
        //concat 将多个observable串行进行发射，只有当前一个observable调用onComplete之后才会继续发射后一个observable
        Observable<String> observable1 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                println("缓存引擎查找本地对应图片资源");
                println("未找到本地图片资源，交由网络引擎获取");
                emitter.onComplete();
            }
        });
        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                println("网络引擎获取到图片资源");
                emitter.onNext("美女图");
                emitter.onComplete();
            }
        });
        Observable.concat(observable1, observable2)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        println("获取到的数据"+s);
                    }
                });
    }

    //flatMap
    private void test04(){
        //flatMap() 将一个observable对象拆分成一个或多个observable对象
        Observable<String[]> observable = Observable.create(new ObservableOnSubscribe<String[]>() {
            @Override
            public void subscribe(ObservableEmitter<String[]> emitter) throws Exception {
                String[] params = {"hello", "what's", "you", "name"};
                emitter.onNext(params);
            }
        });
        observable.flatMap(new Function<String[], ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(final String[] strings) throws Exception {
                return Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        for(String s : strings){
                            emitter.onNext(s);
                        };
                    }
                });
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                println(s);
            }
        });
    }

    //zip
    private void test05(){
        //zip 将多个observable对象合并成一个observable对象
        Observable<String> observable1 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("小明");
                emitter.onNext("小红");
                emitter.onNext("小王");
            }
        });

        Observable<Integer> observable2 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(12);
                emitter.onNext(15);
                emitter.onNext(30);
            }
        });

        Observable.zip(observable1, observable2, new BiFunction<String, Integer, String>() {
            @Override
            public String apply(String s, Integer integer) throws Exception {
                return s + integer + "岁";
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                println(s);
            }
        });

    }

    //interval
    private void test06(){
        Disposable disposable = Observable.interval(1,3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        println("time"+aLong);
                    }
                });
    }

}
