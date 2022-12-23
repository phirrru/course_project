SET NAMES utf8;

--
-- Структура таблицы `appointment`
--

CREATE TABLE `appointment` (
  `appointment_id` int(11) NOT NULL,
  `day` int(11) NOT NULL,
  `doctor_id` int(11) NOT NULL,
  `time` varchar(255) NOT NULL,
  `user_id` int(11) NOT NULL
);

-- --------------------------------------------------------

--
-- Структура таблицы `doctor`
--

CREATE TABLE `doctor` (
  `doctor_id` int(11) NOT NULL,
  `doctor_type_id` int(11) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `user_id` int(11) DEFAULT NULL
);

--
-- Дамп данных таблицы `doctor`
--

INSERT INTO `doctor` (`doctor_id`, `doctor_type_id`, `full_name`, `user_id`) VALUES
(2, 2, 'Петров Федор Дмитриевич', 2),
(3, 3, 'Власов Петр Максимович', 3),
(4, 3, 'Новикова Виктория Александровна', 4),
(5, 4, 'Олейник Виктор Григорьевич', 5),
(6, 4, 'Аксенова Елена Николаевна', 6),
(7, 8, 'Тестовый доктор', 10);

-- --------------------------------------------------------

--
-- Структура таблицы `doctor_type`
--

CREATE TABLE `doctor_type` (
  `doctor_type_id` int(11) NOT NULL,
  `doctor_type` varchar(255) NOT NULL
);

--
-- Дамп данных таблицы `doctor_type`
--

INSERT INTO `doctor_type` (`doctor_type_id`, `doctor_type`) VALUES
(1, 'Акушер-гинеколог'),
(2, 'Оториноларинголог'),
(3, 'Офтальмолог'),
(4, 'Педиатр'),
(5, 'Стоматолог'),
(6, 'Терапевт'),
(7, 'Уролог'),
(8, 'Хирург'),
(9, 'Психиатр'),
(10, 'Психотерапевт');

-- --------------------------------------------------------

--
-- Структура таблицы `patient`
--

CREATE TABLE `patient` (
  `user_id` int(11) NOT NULL,
  `birth_date` varchar(255) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `polis` bigint(20) DEFAULT NULL
);

--
-- Дамп данных таблицы `patient`
--

INSERT INTO `patient` (`user_id`, `birth_date`, `full_name`, `polis`) VALUES
(7, '06.03.2003', 'Овчаренко Анастасия Николаевна', 6026199798091989),
(8, '07.07.2002', 'Левицкий Даниил Романович', 5637289056374895),
(9, '1.1.2001', 'Тестовый пациент', 6573829075143678);

-- --------------------------------------------------------

--
-- Структура таблицы `time_slot`
--

CREATE TABLE `time_slot` (
  `time_slot_id` int(11) NOT NULL,
  `day` int(11) NOT NULL,
  `doctor_id` int(11) NOT NULL,
  `time` varchar(255) NOT NULL
);

--
-- Дамп данных таблицы `time_slot`
--

INSERT INTO `time_slot` (`time_slot_id`, `day`, `doctor_id`, `time`) VALUES
(3, 1, 1, '10:00'),
(4, 1, 1, '10:30'),
(5, 1, 1, '11:00');

-- --------------------------------------------------------

--
-- Структура таблицы `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `active` bit(1) NOT NULL,
  `is_admin` bit(1) DEFAULT NULL,
  `is_doctor` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
);

--
-- Дамп данных таблицы `user`
--

INSERT INTO `user` (`id`, `active`, `is_admin`, `is_doctor`, `password`, `username`) VALUES
(2, b'1', b'0', b'1', 'petrovpass', 'petrov'),
(3, b'1', b'0', b'1', 'vlasovpass', 'vlasov'),
(4, b'1', b'0', b'1', 'novikovapass', 'novikova'),
(5, b'1', b'0', b'1', 'oleynikpass', 'oleynik'),
(6, b'1', b'0', b'1', 'aksenovapass', 'aksenova'),
(7, b'1', b'1', b'0', 'class', 'ovcharenko_a'),
(8, b'1', b'0', b'0', 'levitskypass', 'levitsky'),
(9, b'1', b'1', b'0', 'test', 'test_patient'),
(10, b'1', b'0', b'1', 'test', 'test_doctor');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`appointment_id`);

--
-- Индексы таблицы `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`doctor_id`);

--
-- Индексы таблицы `doctor_type`
--
ALTER TABLE `doctor_type`
  ADD PRIMARY KEY (`doctor_type_id`);

--
-- Индексы таблицы `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`user_id`);

--
-- Индексы таблицы `time_slot`
--
ALTER TABLE `time_slot`
  ADD PRIMARY KEY (`time_slot_id`);

--
-- Индексы таблицы `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `appointment`
--
ALTER TABLE `appointment`
  MODIFY `appointment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблицы `doctor`
--
ALTER TABLE `doctor`
  MODIFY `doctor_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT для таблицы `doctor_type`
--
ALTER TABLE `doctor_type`
  MODIFY `doctor_type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT для таблицы `time_slot`
--
ALTER TABLE `time_slot`
  MODIFY `time_slot_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=177;

--
-- AUTO_INCREMENT для таблицы `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;
