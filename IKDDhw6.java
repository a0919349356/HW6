import java.io.*;

import weka.clusterers.SimpleKMeans;
import weka.core.*;
import weka.core.converters.ArffSaver;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Standardize;

public class IKDDhw6
{
	public static void main(String[] args) throws Exception
	{
		FastVector	atts = new FastVector();
		Instances	data;
		double[]    vals;
		int[]	p = new int[500];
		for(int i=1;i<17;i++)
			atts.addElement(new Attribute("att"+i));
		data = new Instances("MyRelation", atts, 0);
		String filename = "dataset.txt";
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		int cnt = 0;
		while(line != null)
		{
			String[] item = line.split(",");
			vals = new double[data.numAttributes()];
			for(int i=1;i<item.length;i++)
			{
				if(item[i].equals("y"))
					vals[i-1] = 1;
				else if(item[i].equals("n"))
					vals[i-1] = 0;
				else
					vals[i-1] = Utils.missingValue();
			}
			if(item[0].equals("republican"))
				p[cnt++] = 0;
			else
				p[cnt++] = 1;
			data.add(new DenseInstance(1.0, vals));
			line = br.readLine();
		}
		SimpleKMeans clusterer = new SimpleKMeans();
		clusterer.setNumClusters(2);
		clusterer.setPreserveInstancesOrder(true);
		clusterer.buildClusterer(data);
		
		FileWriter fw = new FileWriter("cluster1.csv");
		FileWriter fw2 = new FileWriter("cluster2.csv");
		int a[] = clusterer.getAssignments();
		int ans = 0;
		for(int i=0;i<a.length;i++)
		{
			if(a[i]==0)
			{
				fw.append(Integer.toString(i+1));
				fw.append("\n");
			}
			else
			{
				fw2.append(Integer.toString(i+1));
				fw2.append("\n");
			}
		}
		fw.close();
		fw2.close();
		System.out.println(ans);
	}

}
