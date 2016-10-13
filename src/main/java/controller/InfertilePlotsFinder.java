package controller;

import java.util.List;

import model.Farm;

public interface InfertilePlotsFinder
{
	public List<Integer> getListOfFertilePlots(Farm inFarm);
}
