#language:en
Feature: Поиск товаров по фильтрам и проверка на соответствие

  # Реализация параметризованного теста
  @ui @positive
  Scenario Outline: Проверяем наличие заданного товара и соответствие указанным характеристикам
    Given пользователь открывает поисковик google
    *     пользователь ищет 'citilink' и по заголовку 'Ситилинк - интернет-магазин техники, электроники ...' переходит на веб страницу
    *     пользователь выбирает 'Ситилинк - интернет-магазин техники, электроники ...'
    *     пользователь открывает каталог и выполняет поиск раздела с смартфонами
    When  пользователь выполняет поиск товара по фильтру бренда "<параметр поиска>"
    Then  все названия товаров содержат "<текст для проверки результатов поиска>"


    Examples:
      | параметр поиска | текст для проверки результатов поиска |
      | Apple           | iPhone                                |
      | HONOR           | Honor                                 |





