package ru.paymentscheduler.domain.entity

enum class Month(val fullName: String) {
	JAN("Январь"),
	FEB("Февраль"),
	MAR("Март"),
	APR("Апрель"),
	MAY("Май"),
	JUN("Июнь"),
	JUL("Июль"),
	AUG("Август"),
	SEP("Сентябрь"),
	OCT("Октябрь"),
	NOV("Ноябрь"),
	DEC("Декабрь");

	companion object {

		fun fromFullName(value: String) = Month.values().first { it.fullName == value }

		fun fromInt(value: Int) = Month.values().first { it.ordinal == value }

		fun toList(): List<String> {
			val list = mutableListOf<String>()
			Month.values().forEach {
				list.add(it.fullName)
			}
			return list
		}
	}
}