
# MyBoard

## 완성 예제
![myBoard](https://github.com/unso99/My_Board/assets/94777814/7a9c4aed-1409-468f-8337-6701104a1899)


## 이 프로젝트에서 사용한 기술
- [MVVM](https://ko.wikipedia.org/wiki/%EB%AA%A8%EB%8D%B8-%EB%B7%B0-%EB%B7%B0%EB%AA%A8%EB%8D%B8)
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android?hl=ko)
- [Coroutine](https://developer.android.com/kotlin/coroutines?hl=ko)
- [Flow](https://developer.android.com/kotlin/flow?hl=ko)
- [Room](https://developer.android.com/training/data-storage/room?hl=ko)
- [BindingAdapter](https://developer.android.com/topic/libraries/data-binding/binding-adapters?hl=ko)
- [Retrofit2](https://square.github.io/retrofit/)
- [Clean architecture](https://medium.com/@justfaceit/clean-architecture%EB%8A%94-%EB%AA%A8%EB%B0%94%EC%9D%BC-%EA%B0%9C%EB%B0%9C%EC%9D%84-%EC%96%B4%EB%96%BB%EA%B2%8C-%EB%8F%84%EC%99%80%EC%A3%BC%EB%8A%94%EA%B0%80-1-%EA%B2%BD%EA%B3%84%EC%84%A0-%EA%B3%84%EC%B8%B5%EC%9D%84-%EC%A0%95%EC%9D%98%ED%95%B4%EC%A4%80%EB%8B%A4-b77496744616)
- [Lottie](https://airbnb.io/lottie/#/android)

## test rest api
[https://github.com/cloudchamb3r/be-dog](https://github.com/cloudchamb3r/be-dog)

## 프로젝트의 목적
- 비동기 처리를 위해 Coroutine과 Flow를 활용
- 의존성 주입(DI)을 위해 Hilt를 사용
- Room DB 활용
- MVVM 패턴과 BindingAdapter 활용
- Clean Architecture 적용 및 이해

## 프로젝트를 하면서 느낀점
- 이번 프로젝트에서는 Clean Architecture를 적용시켜보았다. 총 3개의 layer로 분리하여 코드를 짜보았다. 크게 Presentation, domain, data 3개의 계층으로 분리하였다.
  - Presentation Layer : UI와 관련된 모든 처리를 가지고 있는 Layer이다. 이 프로젝트에서는 MVVM 패턴을 사용했기에 `ui`와 `viewmodel`이 포함되어 있다.
  - Domain Layer : 의존성을 가지지 않는 layer이며 비즈니스 로직에 필요한 `Data Model`과 `Usecase`가 포함되어 있다
  - Data Layer : Data들을 contorl하는 layer이다 (CRUD). API 통신과 그 결과로 가져오는 `Data Entity` 내부 `DB(room)`과 `Dao`, 데이터를 사용하기 위한 `Repository`, 데이터를 변환해주는 `mapper`가 포함되어 있다.
- 처음으로 clean architecture를 공부해보고 직접 적용해보았다. 글로만 보았을 때는 감도 안잡혔지만 직접 미니 프로젝트를 진행하면서 적용을 해보니 조금은 감이 잡힌거 같다.
- 이번 프로젝트를 진행하면서도 비동기 처리와 MVVM패턴을 적용해보면서 확실히 전보다 코드를 빠르게 작성할 수 있었다.
- 하지만 아직까지 Hilt를 사용한 DI는 어렵다. Hilt를 적용하고 어플이 build도 되지 않아 어려움을 많이 겪었다. 조금더 DI에 대해서 공부가 필요하다.
- 다음 미니 프로젝트에서는 이번 프로젝트에서 사용한 기술 스택들과 더불어 `jetpack compose`를 적용해보려고 한다.
